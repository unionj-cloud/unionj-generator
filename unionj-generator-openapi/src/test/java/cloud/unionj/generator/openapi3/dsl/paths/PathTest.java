package cloud.unionj.generator.openapi3.dsl.paths;

import cloud.unionj.generator.openapi3.dsl.Reference;
import cloud.unionj.generator.openapi3.dsl.Schema;
import cloud.unionj.generator.openapi3.dsl.servers.Server;
import cloud.unionj.generator.openapi3.model.Openapi3;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cloud.unionj.generator.openapi3.dsl.SchemaHelper;
import org.junit.Test;

import static cloud.unionj.generator.openapi3.dsl.Openapi3.openapi3;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.bool;
import static cloud.unionj.generator.openapi3.dsl.info.Info.info;
import static cloud.unionj.generator.openapi3.dsl.paths.Components.*;
import static cloud.unionj.generator.openapi3.dsl.paths.Responses.responses;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.dsl.paths
 *  date 2020/12/16
 */
public class PathTest {

  @Test
  public void TestPath() throws JsonProcessingException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试");
        ib.version("v1.0.0");
      });

      Server.server(sb -> {
        sb.url("http://www.unionj.com");
      });

      SchemaHelper.batchImport(Components.class);

      Path.path("/hall/onlineSurvey/list", pb -> {
        Post.post(ppb -> {
          ppb.summary("网络调查分页");
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

      Path.path("/hall/offlineSurvey/update", pb -> {
        Post.post(ppb -> {
          ppb.summary("更新信息, 重新提交审核");
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
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    System.out.println(objectMapper.writeValueAsString(openapi3));
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

      Path.path("/oss/upload", pb -> {
        Post.post(ppb -> {
          ppb.summary("上传附件");
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
  }
}