package cloud.unionj.generator.service;

import cloud.unionj.generator.openapi3.dsl.Reference;
import cloud.unionj.generator.openapi3.dsl.Schema;
import cloud.unionj.generator.openapi3.dsl.SchemaHelper;
import cloud.unionj.generator.openapi3.dsl.paths.*;
import cloud.unionj.generator.openapi3.dsl.servers.Server;
import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.service.apicloud.config.Aliyun;
import cloud.unionj.generator.service.apicloud.handler.TaskHandler;
import cloud.unionj.generator.service.apicloud.trigger.CloudTaskTrigger;
import cloud.unionj.generator.service.apicloud.utils.DateTimeUtils;
import com.aliyuncs.devops_rdc.model.v20200303.CreateDevopsProjectTaskResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static cloud.unionj.generator.openapi3.dsl.Openapi3.openapi3;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.bool;
import static cloud.unionj.generator.openapi3.dsl.info.Info.info;
import static cloud.unionj.generator.openapi3.dsl.paths.Responses.responses;
import static cloud.unionj.generator.service.Components.ResultDTOString;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * @description: description
 * @date: 2021-01-04 09:50
 **/
public class TaskTest {

    @SneakyThrows
    @Test
    public void create() {
        CreateDevopsProjectTaskResponse response = TaskHandler.create(tr -> {
            tr.setContent("测试内容。。"); // 标题
            tr.setNote("云效任务测试"); // 备注
            tr.setPriority(0); // 优先级：0：普通（默认值）1：紧急2：非常紧急
        });
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        System.out.println(objectMapper.writeValueAsString(response));
    }

    @Test
    public void parse() {
//        Aliyun instance = Aliyun.INSTANCE;
//        String id = TaskHandler.fetchScenarioFieldConfigId(ScenarioFieldConfig.TASK);
//        System.out.println(instance);
    }


    @Test
    public void TestPath2() throws JsonProcessingException {
        Openapi3 openapi3 = openapi3(ob -> {
            info(ib -> {
                ib.title("测试");
                ib.version("v1.0.0");
            });

            Server.server(sb -> {
                sb.url("http://www.unionj.com");
            });

            SchemaHelper.batchImport(Components.class);

            Path.path("/oss/upload1", pb -> {
                Post.post(ppb -> {
                    ppb.summary("上传附件1");
                    ppb.tags("attachment");

                    Parameter.parameter(para -> {
                        para.required(false);
                        para.in("query");
                        para.name("returnKey");
                        para.schema(bool);
                    });

                    RequestBody.requestBody(rb -> {
                        rb.required(true);
                        rb.content(Content.content(cb -> {
                            cb.multipartFormData(MediaType.mediaType(mb -> {
                                mb.schema(Schema.schema(upload -> {
                                    upload.type("object");
                                    upload.properties("file", Schema.schema(file -> {
                                        file.type("string");
                                        file.format("binary");
                                    }));
                                }));
                            }));
                        }));
                    });

                    responses(rb -> {
                        rb.response200(Response.response(rrb -> {
                            rrb.content(Content.content(cb -> {
                                cb.applicationJson(MediaType.mediaType(mb -> {
                                    mb.schema(Reference.reference(rrrb -> {
                                        rrrb.ref(ResultDTOString.getTitle());
                                    }));
                                }));
                            }));
                        }));
                    });
                });
            });
        });
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(objectMapper.writeValueAsString(openapi3));
        new CloudTaskTrigger().call(openapi3, "/Users/dingxu/config/aliyun.properties");
    }

    @Test
    public void test(){
        System.out.println(DateTimeUtils.nowStringByLocal());
        System.out.println(DateTimeUtils.nowStringByUtc());
    }
}
