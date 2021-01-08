package cloud.unionj.generator.service;

import cloud.unionj.generator.openapi3.dsl.Reference;
import cloud.unionj.generator.openapi3.dsl.SchemaHelper;
import cloud.unionj.generator.openapi3.dsl.paths.*;
import cloud.unionj.generator.openapi3.dsl.servers.Server;
import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.service.apicloud.config.AliyunConfigLoad;
import cloud.unionj.generator.service.apicloud.handler.ProjectHandler;
import cloud.unionj.generator.service.apicloud.handler.TaskHandler;
import cloud.unionj.generator.service.apicloud.trigger.CloudTaskTrigger;
import cloud.unionj.generator.service.apicloud.trigger.CloudTrigger;
import cloud.unionj.generator.service.apicloud.utils.AcsClient;
import cloud.unionj.generator.service.apicloud.utils.ConsolePrint;
import com.aliyuncs.devops_rdc.model.v20200303.CreateDevopsProjectResponse;
import com.aliyuncs.devops_rdc.model.v20200303.CreateDevopsProjectTaskResponse;
import com.aliyuncs.devops_rdc.model.v20200303.GetDevopsProjectInfoRequest;
import com.aliyuncs.devops_rdc.model.v20200303.GetDevopsProjectInfoResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;

import static cloud.unionj.generator.openapi3.dsl.Openapi3.openapi3;
import static cloud.unionj.generator.openapi3.dsl.info.Info.info;
import static cloud.unionj.generator.openapi3.dsl.paths.Responses.responses;
import static cloud.unionj.generator.service.Components.*;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * description: description
 * date: 2021-01-04 09:50
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
    public void TestPath2(){
        Openapi3 openapi3 = openapi3(ob -> {
            info(ib -> {
                ib.title("测试创建项目、任务流程1");
                ib.description("项目描述");
                ib.version("v1.0.0");
            });

            Server.server(sb -> {
                sb.url("http://www.unionj.com");
            });

            SchemaHelper.batchImport(Components.class);

            Path.path("/2021/0108", pb -> {
                Post.post(ppb -> {
                    ppb.summary("网络调查分页");
                    ppb.description("测试描述1");
                    ppb.tags("hall_onlinesurvey");

                    RequestBody.requestBody(rb -> {
                        rb.required(true);
                        rb.content(Content.content(cb -> {
                            cb.applicationJson(MediaType.mediaType(mb -> {
                                mb.schema(Reference.reference(rrb -> {
                                    rrb.ref(UserDate.getTitle());
                                }));
                            }));
                        }));
                    });

                    responses(rb -> {
                        rb.response200(Response.response(rrb -> {
                            rrb.content(Content.content(cb -> {
                                cb.applicationJson(MediaType.mediaType(mb -> {
                                    mb.schema(Reference.reference(rrrb -> {
                                        rrrb.ref(ResultDTOListUserDate.getTitle());
                                    }));
                                }));
                            }));
                        }));
                    });
                });
            });

            Path.path("/2021/0108/update", pb -> {
                Post.post(ppb -> {
                    ppb.summary("更新信息, 重新提交审核");
                    ppb.description("测试描述2");
                    ppb.tags("hall_offlinesurvey");

                    RequestBody.requestBody(rb -> {
                        rb.required(true);
                        rb.content(Content.content(cb -> {
                            cb.applicationJson(MediaType.mediaType(mb -> {
                                mb.schema(Reference.reference(rrb -> {
                                    rrb.ref(UserInteger.getTitle());
                                }));
                            }));
                        }));
                    });

                    responses(rb -> {
                        rb.response200(Response.response(rrb -> {
                            rrb.content(Content.content(cb -> {
                                cb.applicationJson(MediaType.mediaType(mb -> {
                                    mb.schema(Reference.reference(rrrb -> {
                                        rrrb.ref(ResultDTOListUserInteger.getTitle());
                                    }));
                                }));
                            }));
                        }));
                    });
                });
            });
        });

        AliyunConfigLoad.load("/Users/dingxu/config/aliyun.properties");
        new CloudTaskTrigger().create(openapi3);
    }

    @SneakyThrows
    @Test
    public void test(){
        AliyunConfigLoad.load("/Users/dingxu/config/aliyun.properties");
        CreateDevopsProjectResponse response = ProjectHandler.create(ob -> {
            ob.setName("测试12：07"); // 项目名称
            ob.setDescription("描述12: 07"); // 项目描述
        });
        String projectId = response.getObject();
        GetDevopsProjectInfoRequest getProjectInfoRequest=new GetDevopsProjectInfoRequest();
        getProjectInfoRequest.setOrgId(AliyunConfigLoad.AliyunConfig.getOrgId());
        getProjectInfoRequest.setProjectId(projectId);
        getProjectInfoRequest.setSysEndpoint(AliyunConfigLoad.AliyunConfig.getEndPoint());
        GetDevopsProjectInfoResponse acsResponse = AcsClient.get().getAcsResponse(getProjectInfoRequest);
        ConsolePrint.pretty(acsResponse);
    }

}
