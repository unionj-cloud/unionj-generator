package com.tsingxiao.unionj.generator.backend;

import com.tsingxiao.unionj.generator.backend.docparser.BackendDocParser;
import com.tsingxiao.unionj.generator.backend.docparser.entity.Backend;
import com.tsingxiao.unionj.generator.backend.springboot.SpringbootFolderGenerator;
import com.tsingxiao.unionj.generator.openapi3.dsl.SchemaHelper;
import com.tsingxiao.unionj.generator.openapi3.model.Openapi3;
import org.junit.Test;

import java.io.IOException;

import static com.tsingxiao.unionj.generator.backend.Components.*;
import static com.tsingxiao.unionj.generator.openapi3.dsl.Openapi3.openapi3;
import static com.tsingxiao.unionj.generator.openapi3.dsl.Reference.reference;
import static com.tsingxiao.unionj.generator.openapi3.dsl.info.Info.info;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.Content.content;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.MediaType.mediaType;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.Path.path;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.Post.post;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.RequestBody.requestBody;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.Response.response;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.Responses.responses;
import static com.tsingxiao.unionj.generator.openapi3.dsl.servers.Server.server;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl.paths
 * @date:2020/12/16
 */
public class PathTest {

  @Test
  public void TestPath() throws IOException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试");
        ib.version("v1.0.0");
      });

      server(sb -> {
        sb.url("http://www.unionj.com");
      });

      SchemaHelper.batchImport(Components.class);

      path("/hall/onlineSurvey/list", pb -> {
        post(ppb -> {
          ppb.summary("网络调查分页");
          ppb.tags("hall_onlinesurvey");

          requestBody(rb -> {
            rb.required(true);
            rb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrb -> {
                  rrb.ref(UserDate.getTitle());
                }));
              }));
            }));
          });

          responses(rb -> {
            rb.response200(response(rrb -> {
              rrb.content(content(cb -> {
                cb.applicationJson(mediaType(mb -> {
                  mb.schema(reference(rrrb -> {
                    rrrb.ref(ResultDTOListUserDate.getTitle());
                  }));
                }));
              }));
            }));
          });
        });
      });

      path("/hall/offlineSurvey/update", pb -> {
        post(ppb -> {
          ppb.summary("更新信息, 重新提交审核");
          ppb.tags("hall_offlinesurvey");

          requestBody(rb -> {
            rb.required(true);
            rb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrb -> {
                  rrb.ref(UserInteger.getTitle());
                }));
              }));
            }));
          });

          responses(rb -> {
            rb.response200(response(rrb -> {
              rrb.content(content(cb -> {
                cb.applicationJson(mediaType(mb -> {
                  mb.schema(reference(rrrb -> {
                    rrrb.ref(ResultDTOListUserInteger.getTitle());
                  }));
                }));
              }));
            }));
          });
        });
      });
    });
    Backend backend = BackendDocParser.parse(openapi3);
    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
    springbootFolderGenerator.generate();
  }

  @Test
  public void Test1() {
    String name = "ResultDTO<List<User<Integer>>>.java";
    String s = name.replaceAll("<.*>", "");
    System.out.println(s);
  }
}
