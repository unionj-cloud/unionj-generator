package cloud.unionj.generator.kanban;

import cloud.unionj.generator.kanban.config.AliyunConfigLoad;
import cloud.unionj.generator.kanban.handler.ProjectHandler;
import cloud.unionj.generator.kanban.handler.TaskHandler;
import cloud.unionj.generator.kanban.trigger.CloudTaskTrigger;
import cloud.unionj.generator.kanban.utils.AcsClient;
import cloud.unionj.generator.kanban.utils.ConsolePrint;
import cloud.unionj.generator.openapi3.dsl.Reference;
import cloud.unionj.generator.openapi3.dsl.SchemaHelper;
import cloud.unionj.generator.openapi3.dsl.paths.*;
import cloud.unionj.generator.openapi3.dsl.servers.Server;
import cloud.unionj.generator.openapi3.model.Openapi3;
import com.aliyuncs.devops_rdc.model.v20200303.CreateDevopsProjectResponse;
import com.aliyuncs.devops_rdc.model.v20200303.CreateDevopsProjectTaskResponse;
import com.aliyuncs.devops_rdc.model.v20200303.GetDevopsProjectInfoRequest;
import com.aliyuncs.devops_rdc.model.v20200303.GetDevopsProjectInfoResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;
import static cloud.unionj.generator.kanban.Components.*;


import static cloud.unionj.generator.openapi3.dsl.Openapi3.openapi3;
import static cloud.unionj.generator.openapi3.dsl.info.Info.info;
import static cloud.unionj.generator.openapi3.dsl.paths.Responses.responses;

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
                ib.title("测试重复任务bug");
                ib.description("项目描述");
                ib.version("v1.0.0");
            });

            Server.server(sb -> {
                sb.url("http://www.unionj.com");
            });

            SchemaHelper.batchImport(Components.class);

            Path.path("/2021/0116", pb -> {
                Post.post(ppb -> {
                    ppb.summary("midnight test continue...");
                    ppb.description("测试描述");
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

            Path.path("/2021/0116/update", pb -> {
                Post.post(ppb -> {
                    ppb.summary("midnight test continue....");
                    ppb.description("测试描述");
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

        AliyunConfigLoad.load("/Users/dingxu/config/aliyun_new.properties");
        CloudTaskTrigger.call(openapi3);
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
