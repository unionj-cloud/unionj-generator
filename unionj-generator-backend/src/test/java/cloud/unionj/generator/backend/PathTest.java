package cloud.unionj.generator.backend;

import cloud.unionj.generator.backend.docparser.BackendDocParser;
import cloud.unionj.generator.backend.docparser.entity.Backend;
import cloud.unionj.generator.backend.springboot.SpringbootFolderGenerator;
import cloud.unionj.generator.openapi3.dsl.SchemaHelper;
import cloud.unionj.generator.openapi3.model.Openapi3;
import org.junit.Test;

import java.io.IOException;

import static cloud.unionj.generator.backend.Components.*;
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
import static cloud.unionj.generator.openapi3.dsl.servers.Server.server;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.dsl.paths
 *  date 2020/12/16
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

  @Test
  public void Test2() throws IOException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试");
        ib.version("v1.0.0");
      });

      server(sb -> {
        sb.url("http://www.unionj.com");
      });

      SchemaHelper.batchImport(Components.class);

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
              cb.applicationOctetStream(mediaType(mb -> {
                mb.schema(schema(upload -> {
                  upload.type("string");
                  upload.format("binary");
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
  public void TestFileRequired() throws IOException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试");
        ib.version("v1.0.0");
      });

      server(sb -> {
        sb.url("http://www.unionj.com");
      });

      SchemaHelper.batchImport(Components.class);

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
                  upload.required("file");
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
  public void TestFileNotRequired() throws IOException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试");
        ib.version("v1.0.0");
      });

      server(sb -> {
        sb.url("http://www.unionj.com");
      });

      SchemaHelper.batchImport(Components.class);

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
  public void TestFiles() throws IOException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试");
        ib.version("v1.0.0");
      });

      server(sb -> {
        sb.url("http://www.unionj.com");
      });

      SchemaHelper.batchImport(Components.class);

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
                  upload.properties("files", schema(files -> {
                    files.type("array");
                    files.items(schema(file -> {
                      file.type("string");
                      file.format("binary");
                    }));
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
  public void TestFilesWithOtherFields() throws IOException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试");
        ib.version("v1.0.0");
      });

      server(sb -> {
        sb.url("http://www.unionj.com");
      });

      SchemaHelper.batchImport(Components.class);

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
                  upload.properties("files", schema(files -> {
                    files.type("array");
                    files.items(schema(file -> {
                      file.type("string");
                      file.format("binary");
                    }));
                  }));
                  upload.properties("password", string);
                  upload.properties("username", string);
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
  public void TestFilesWithOtherFieldsRequired() throws IOException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试");
        ib.version("v1.0.0");
      });

      server(sb -> {
        sb.url("http://www.unionj.com");
      });

      SchemaHelper.batchImport(Components.class);

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
                  upload.required("username");
                  upload.properties("files", schema(files -> {
                    files.type("array");
                    files.items(schema(file -> {
                      file.type("string");
                      file.format("binary");
                    }));
                  }));
                  upload.properties("username", string);
                  upload.properties("password", string);
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
  public void TestRequestBodyPlainText() throws IOException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试");
        ib.version("v1.0.0");
      });

      server(sb -> {
        sb.url("http://www.unionj.com");
      });

      SchemaHelper.batchImport(Components.class);

      path("/shortLink", pb -> {
        post(ppb -> {
          ppb.summary("获取短链接");
          ppb.tags("survey");

          requestBody(rb -> {
            rb.required(true);
            rb.content(content(cb -> {
              cb.textPlain(mediaType(mb -> {
                mb.schema(string);
              }));
            }));
          });

          responses(rb -> {
            rb.response200(response(rrb -> {
              rrb.description("OK");
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
    });
    Backend backend = BackendDocParser.parse(openapi3);
    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
    springbootFolderGenerator.generate();
  }

  @Test
  public void TestRequestParamDefaultValue() throws IOException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试");
        ib.version("v1.0.0");
      });

      server(sb -> {
        sb.url("http://www.unionj.com");
      });

      SchemaHelper.batchImport(Components.class);

      path("/shop/info", pb -> {
        get(ppb -> {
          ppb.summary("店铺详情");
          ppb.tags("shop");

          parameter(para -> {
            para.required(false);
            para.in("query");
            para.name("id");
            para.schema(int32);
          });

          parameter(para -> {
            para.required(false);
            para.in("query");
            para.name("queryStat");
            para.description("是否查询统计信息");
            para.schema(schema(queryStat -> {
              queryStat.type("boolean");
              queryStat.defaultValue(false);
            }));
          });

          responses(rb -> {
            rb.response200(response(rrb -> {
              rrb.content(content(cb -> {
                cb.applicationJson(mediaType(mb -> {
                  mb.schema(reference(rrrb -> {
                    rrrb.ref(ResultDTOMapStringString.getTitle());
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
  public void TestParameterOrder() throws IOException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试");
        ib.version("v1.0.0");
      });

      server(sb -> {
        sb.url("http://www.unionj.com");
      });

      SchemaHelper.batchImport(Components.class);

      path("/shop/info", pb -> {
        get(ppb -> {
          ppb.summary("店铺详情");
          ppb.tags("shop");

          parameter(para -> {
            para.required(false);
            para.in("query");
            para.name("id");
            para.schema(int32);
          });

          parameter(para -> {
            para.required(false);
            para.in("query");
            para.name("queryStat");
            para.description("是否查询统计信息");
            para.schema(schema(queryStat -> {
              queryStat.type("boolean");
              queryStat.defaultValue(false);
            }));
          });

          responses(rb -> {
            rb.response200(response(rrb -> {
              rrb.content(content(cb -> {
                cb.applicationJson(mediaType(mb -> {
                  mb.schema(reference(rrrb -> {
                    rrrb.ref(ResultDTOMapStringString.getTitle());
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
}
