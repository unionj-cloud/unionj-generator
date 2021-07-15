package cloud.unionj.generator.service;

import cloud.unionj.generator.openapi3.dsl.servers.Server;
import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.service.docparser.ServiceDocParser;
import cloud.unionj.generator.service.docparser.entity.BizServer;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

import static cloud.unionj.generator.openapi3.dsl.Openapi3.openapi3;
import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.Schema.schema;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.*;
import static cloud.unionj.generator.openapi3.dsl.info.Info.info;
import static cloud.unionj.generator.openapi3.dsl.paths.Content.content;
import static cloud.unionj.generator.openapi3.dsl.paths.Get.get;
import static cloud.unionj.generator.openapi3.dsl.paths.MediaType.mediaType;
import static cloud.unionj.generator.openapi3.dsl.paths.Parameter.parameter;
import static cloud.unionj.generator.openapi3.dsl.paths.Path.path;
import static cloud.unionj.generator.openapi3.dsl.paths.Post.post;
import static cloud.unionj.generator.openapi3.dsl.paths.RequestBody.requestBody;
import static cloud.unionj.generator.openapi3.dsl.paths.Response.response;
import static cloud.unionj.generator.openapi3.dsl.paths.Responses.responses;
import static cloud.unionj.generator.service.ComponentsDesigner.ResultDTOString;
import static cloud.unionj.generator.service.ComponentsDesigner.SearchJobPageResult;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.service
 * date 2020/11/27
 */
public class TypesTsGeneratorTest {

  @Test
  public void generate() throws IOException {
    try (BufferedInputStream is = new BufferedInputStream(ClassLoader.getSystemResourceAsStream("test1.json"))) {
      BizServer bizServer = ServiceDocParser.parse(is);
      TypesTsGenerator typesTsGenerator = new TypesTsGenerator(bizServer.getTypes());
      String outputFile = typesTsGenerator.generate();
      File file = new File(outputFile);
      Assert.assertTrue(file.exists());
    }
  }

  @Test
  public void generate1() throws IOException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试");
        ib.version("v1.0.0");
      });

      Server.server(sb -> {
        sb.url("http://www.unionj.com");
      });

      path("/clshenbao/form/save", pb -> {
        post(ppb -> {
          ppb.summary("材料申报：保存form表单");
          ppb.tags("材料申报模块");
          ppb.tags("clshenbaoForm");

          requestBody(rb -> {
            rb.required(true);
            rb.description("这是payload");
            rb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(schema(sb -> {
                  sb.type("object");
                  sb.title("PostClshenbaoFormSavePayload");
                  sb.properties("userID", int64);
                  sb.properties("fields", stringArray);
                }));
              }));
            }));
          });

          parameter(para -> {
            para.required(true);
            para.in("query");
            para.name("key");
            para.description("键");
            para.schema(string);
          });

          parameter(para -> {
            para.required(false);
            para.in("query");
            para.name("style");
            para.description("样式");
            para.schema(string);
          });

          responses(rb -> {
            rb.response200(response(rrb -> {
              rrb.description("返回参数");
              rrb.content(content(cb -> {
                cb.applicationJson(mediaType(mb -> {
                  mb.schema(reference(rrrb -> {
                    rrrb.ref(SearchJobPageResult.getxTitle());
                  }));
                }));
              }));
            }));
          });
        });
      });
    });

    BizServer bizServer = ServiceDocParser.parse(openapi3);
    ServiceFolderGenerator serviceFolderGenerator = new ServiceFolderGenerator.Builder(bizServer).zip(false).build();
    serviceFolderGenerator.generate();
  }

  @Test
  public void generate2() throws IOException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试");
        ib.version("v1.0.0");
      });

      Server.server(sb -> {
        sb.url("http://www.unionj.com");
      });

      path("/oss/upload", pb -> {
        post(ppb -> {
          ppb.summary("上传附件");
          ppb.tags("attachment");

          parameter(para -> {
            para.required(false);
            para.in("query");
            para.name("returnKey");
            para.schema(bool);
          });

          requestBody(rb -> {
            rb.required(true);
            rb.content(content(cb -> {
              cb.multipartFormData(mediaType(mb -> {
                mb.schema(schema(upload -> {
                  upload.type("object");
                  upload.properties("file", schema(file -> {
                    file.type("string");
                    file.format("binary");
                  }));
                }));
              }));
            }));
          });

          responses(rb -> {
            rb.response200(response(rrb -> {
              rrb.content(content(cb -> {
                cb.applicationJson(mediaType(mb -> {
                  mb.schema(reference(rrrb -> {
                    rrrb.ref(ResultDTOString.getxTitle());
                  }));
                }));
              }));
            }));
          });
        });
      });
    });

    BizServer bizServer = ServiceDocParser.parse(openapi3);
    ServiceFolderGenerator serviceFolderGenerator = new ServiceFolderGenerator.Builder(bizServer).zip(false).build();
    serviceFolderGenerator.generate();
  }

  @Test
  public void generate3() throws IOException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试");
        ib.version("v1.0.0");
      });

      Server.server(sb -> {
        sb.url("http://www.unionj.com");
      });

      path("/oss/get", pb -> {
        get(ppb -> {
          ppb.summary("获取附件");
          ppb.tags("attachment");

          parameter(para -> {
            para.required(true);
            para.in("query");
            para.name("key");
            para.schema(string);
          });

          parameter(para -> {
            para.required(false);
            para.in("query");
            para.name("style");
            para.schema(string);
          });

          responses(rb -> {
            rb.response200(response(rrb -> {
              rrb.content(content(cb -> {
                cb.applicationOctetStream(mediaType(mb -> {
                  mb.schema(schema(download -> {
                    download.type("string");
                    download.format("binary");
                  }));
                }));
              }));
            }));
          });
        });
      });
    });

    BizServer bizServer = ServiceDocParser.parse(openapi3);
    ServiceFolderGenerator serviceFolderGenerator = new ServiceFolderGenerator.Builder(bizServer).zip(false).build();
    serviceFolderGenerator.generate();
  }
}
