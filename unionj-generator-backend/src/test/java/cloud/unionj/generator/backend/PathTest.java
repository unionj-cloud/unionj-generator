package cloud.unionj.generator.backend;

import cloud.unionj.generator.backend.docparser.BackendDocParser;
import cloud.unionj.generator.backend.docparser.entity.Backend;
import cloud.unionj.generator.backend.springboot.SpringbootFolderGenerator;
import cloud.unionj.generator.openapi3.PathConfig;
import cloud.unionj.generator.openapi3.dsl.Reference;
import cloud.unionj.generator.openapi3.dsl.paths.*;
import cloud.unionj.generator.openapi3.dsl.servers.Server;
import cloud.unionj.generator.openapi3.expression.paths.ParameterBuilder;
import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.openapi3.model.Schema;
import cloud.unionj.generator.openapi3.model.paths.Parameter;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;

import static cloud.unionj.generator.backend.Components.*;
import static cloud.unionj.generator.openapi3.PathHelper.post;
import static cloud.unionj.generator.openapi3.dsl.Generic.generic;
import static cloud.unionj.generator.openapi3.dsl.Openapi3.openapi3;
import static cloud.unionj.generator.openapi3.dsl.Schema.schema;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.*;
import static cloud.unionj.generator.openapi3.dsl.info.Info.info;
import static cloud.unionj.generator.openapi3.dsl.paths.Responses.responses;
import static cloud.unionj.generator.openapi3.dsl.servers.Server.server;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.dsl.paths
 * date 2020/12/16
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

      post("/hall/onlineSurvey/list", PathConfig.builder()
          .summary("网络调查分页")
          .tags(new String[]{"网络调查", "HallOnlineSurvey"})
          .reqSchema(SearchJobPageResult)
          .respSchema(SearchJobPageResult)
          .build());

      post("/hall/offlineSurvey/update", PathConfig.builder()
          .summary("更新信息, 重新提交审核")
          .tags(new String[]{"网络调查", "HallOfflinesurvey"})
          .reqSchema(SearchJobPageResult)
          .respSchema(SearchJobPageResult)
          .build());

      post("/admin/onlineSurvey/top/update", PathConfig.builder()
          .summary("网络调查任务置顶")
          .tags(new String[]{"admin_onlinesurvey"})
          .parameters(new Parameter[]{
              ParameterBuilder.builder().name("id").in(Parameter.InEnum.QUERY).required(true).schema(string).build(),
              ParameterBuilder.builder().name("top").in(Parameter.InEnum.QUERY).required(true).schema(int32).build(),
          })
          .respSchema(SearchJobPageResult)
          .build());
    });
    Backend backend = BackendDocParser.parse(openapi3);
    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
    springbootFolderGenerator.generate();
  }

  @SneakyThrows
  @Test
  public void TestPath2() {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试创建项目、任务流程1");
        ib.description("项目描述");
        ib.version("v1.0.0");
      });

      Server.server(sb -> {
        sb.url("http://www.unionj.com");
      });

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

//  @Test
//  public void Test2() throws IOException {
//    Openapi3 openapi3 = openapi3(ob -> {
//      info(ib -> {
//        ib.title("测试");
//        ib.version("v1.0.0");
//      });
//
//      server(sb -> {
//        sb.url("http://www.unionj.com");
//      });
//
//      path("/oss/upload", pb -> {
//        post(ppb -> {
//          ppb.summary("上传附件");
//          ppb.tags("attachment");
//
//          parameter(para -> {
//            para.required(false);
//            para.in("query");
//            para.name("returnKey");
//            para.schema(bool);
//          });
//
//          requestBody(rb -> {
//            rb.required(true);
//            rb.content(content(cb -> {
//              cb.applicationOctetStream(mediaType(mb -> {
//                mb.schema(schema(upload -> {
//                  upload.type("string");
//                  upload.format("binary");
//                }));
//              }));
//            }));
//          });
//
//          responses(rb -> {
//            rb.response200(response(rrb -> {
//              rrb.content(content(cb -> {
//                cb.applicationJson(mediaType(mb -> {
//                  mb.schema(reference(rrrb -> {
//                    rrrb.ref(SearchJobPageResult.getTitle());
//                  }));
//                }));
//              }));
//            }));
//          });
//        });
//      });
//    });
//    Backend backend = BackendDocParser.parse(openapi3);
//    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
//    springbootFolderGenerator.generate();
//  }
//
//  @Test
//  public void TestFileRequired() throws IOException {
//    Openapi3 openapi3 = openapi3(ob -> {
//      info(ib -> {
//        ib.title("测试");
//        ib.version("v1.0.0");
//      });
//
//      server(sb -> {
//        sb.url("http://www.unionj.com");
//      });
//
//      path("/oss/upload", pb -> {
//        post(ppb -> {
//          ppb.summary("上传附件");
//          ppb.tags("attachment");
//
//          parameter(para -> {
//            para.required(false);
//            para.in("query");
//            para.name("returnKey");
//            para.schema(bool);
//          });
//
//          requestBody(rb -> {
//            rb.required(true);
//            rb.content(content(cb -> {
//              cb.multipartFormData(mediaType(mb -> {
//                mb.schema(schema(upload -> {
//                  upload.type("object");
//                  upload.required("file");
//                  upload.properties("file", schema(file -> {
//                    file.type("string");
//                    file.format("binary");
//                  }));
//                }));
//              }));
//            }));
//          });
//
//          responses(rb -> {
//            rb.response200(response(rrb -> {
//              rrb.content(content(cb -> {
//                cb.applicationJson(mediaType(mb -> {
//                  mb.schema(reference(rrrb -> {
//                    rrrb.ref(SearchJobPageResult.getTitle());
//                  }));
//                }));
//              }));
//            }));
//          });
//        });
//      });
//    });
//    Backend backend = BackendDocParser.parse(openapi3);
//    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
//    springbootFolderGenerator.generate();
//  }
//
//  @Test
//  public void TestFileNotRequired() throws IOException {
//    Openapi3 openapi3 = openapi3(ob -> {
//      info(ib -> {
//        ib.title("测试");
//        ib.version("v1.0.0");
//      });
//
//      server(sb -> {
//        sb.url("http://www.unionj.com");
//      });
//
//      path("/oss/upload", pb -> {
//        post(ppb -> {
//          ppb.summary("上传附件");
//          ppb.tags("attachment");
//
//          parameter(para -> {
//            para.required(false);
//            para.in("query");
//            para.name("returnKey");
//            para.schema(bool);
//          });
//
//          requestBody(rb -> {
//            rb.required(true);
//            rb.content(content(cb -> {
//              cb.multipartFormData(mediaType(mb -> {
//                mb.schema(schema(upload -> {
//                  upload.type("object");
//                  upload.properties("file", schema(file -> {
//                    file.type("string");
//                    file.format("binary");
//                  }));
//                }));
//              }));
//            }));
//          });
//
//          responses(rb -> {
//            rb.response200(response(rrb -> {
//              rrb.content(content(cb -> {
//                cb.applicationJson(mediaType(mb -> {
//                  mb.schema(reference(rrrb -> {
//                    rrrb.ref(SearchJobPageResult.getTitle());
//                  }));
//                }));
//              }));
//            }));
//          });
//        });
//      });
//    });
//    Backend backend = BackendDocParser.parse(openapi3);
//    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
//    springbootFolderGenerator.generate();
//  }
//
//  @Test
//  public void TestFiles() throws IOException {
//    Openapi3 openapi3 = openapi3(ob -> {
//      info(ib -> {
//        ib.title("测试");
//        ib.version("v1.0.0");
//      });
//
//      server(sb -> {
//        sb.url("http://www.unionj.com");
//      });
//
//      path("/oss/upload", pb -> {
//        post(ppb -> {
//          ppb.summary("上传附件");
//          ppb.tags("attachment");
//
//          parameter(para -> {
//            para.required(false);
//            para.in("query");
//            para.name("returnKey");
//            para.schema(bool);
//          });
//
//          requestBody(rb -> {
//            rb.required(true);
//            rb.content(content(cb -> {
//              cb.multipartFormData(mediaType(mb -> {
//                mb.schema(schema(upload -> {
//                  upload.type("object");
//                  upload.properties("files", schema(files -> {
//                    files.type("array");
//                    files.items(schema(file -> {
//                      file.type("string");
//                      file.format("binary");
//                    }));
//                  }));
//                }));
//              }));
//            }));
//          });
//
//          responses(rb -> {
//            rb.response200(response(rrb -> {
//              rrb.content(content(cb -> {
//                cb.applicationJson(mediaType(mb -> {
//                  mb.schema(reference(rrrb -> {
//                    rrrb.ref(SearchJobPageResult.getTitle());
//                  }));
//                }));
//              }));
//            }));
//          });
//        });
//      });
//    });
//    Backend backend = BackendDocParser.parse(openapi3);
//    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
//    springbootFolderGenerator.generate();
//  }
//
//  @Test
//  public void TestFilesWithOtherFields() throws IOException {
//    Openapi3 openapi3 = openapi3(ob -> {
//      info(ib -> {
//        ib.title("测试");
//        ib.version("v1.0.0");
//      });
//
//      server(sb -> {
//        sb.url("http://www.unionj.com");
//      });
//
//      path("/oss/upload", pb -> {
//        post(ppb -> {
//          ppb.summary("上传附件");
//          ppb.tags("attachment");
//
//          parameter(para -> {
//            para.required(false);
//            para.in("query");
//            para.name("returnKey");
//            para.schema(bool);
//          });
//
//          requestBody(rb -> {
//            rb.required(true);
//            rb.content(content(cb -> {
//              cb.multipartFormData(mediaType(mb -> {
//                mb.schema(schema(upload -> {
//                  upload.type("object");
//                  upload.properties("files", schema(files -> {
//                    files.type("array");
//                    files.items(schema(file -> {
//                      file.type("string");
//                      file.format("binary");
//                    }));
//                  }));
//                  upload.properties("password", string);
//                  upload.properties("username", string);
//                }));
//              }));
//            }));
//          });
//
//          responses(rb -> {
//            rb.response200(response(rrb -> {
//              rrb.content(content(cb -> {
//                cb.applicationJson(mediaType(mb -> {
//                  mb.schema(reference(rrrb -> {
//                    rrrb.ref(SearchJobPageResult.getTitle());
//                  }));
//                }));
//              }));
//            }));
//          });
//        });
//      });
//    });
//    Backend backend = BackendDocParser.parse(openapi3);
//    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
//    springbootFolderGenerator.generate();
//  }
//
//  @Test
//  public void TestFilesWithOtherFieldsRequired() throws IOException {
//    Openapi3 openapi3 = openapi3(ob -> {
//      info(ib -> {
//        ib.title("测试");
//        ib.version("v1.0.0");
//      });
//
//      server(sb -> {
//        sb.url("http://www.unionj.com");
//      });
//
//      path("/oss/upload", pb -> {
//        post(ppb -> {
//          ppb.summary("上传附件");
//          ppb.tags("attachment");
//
//          parameter(para -> {
//            para.required(false);
//            para.in("query");
//            para.name("returnKey");
//            para.schema(bool);
//          });
//
//          requestBody(rb -> {
//            rb.required(true);
//            rb.content(content(cb -> {
//              cb.multipartFormData(mediaType(mb -> {
//                mb.schema(schema(upload -> {
//                  upload.type("object");
//                  upload.required("username");
//                  upload.properties("files", schema(files -> {
//                    files.type("array");
//                    files.items(schema(file -> {
//                      file.type("string");
//                      file.format("binary");
//                    }));
//                  }));
//                  upload.properties("username", string);
//                  upload.properties("password", string);
//                }));
//              }));
//            }));
//          });
//
//          responses(rb -> {
//            rb.response200(response(rrb -> {
//              rrb.content(content(cb -> {
//                cb.applicationJson(mediaType(mb -> {
//                  mb.schema(reference(rrrb -> {
//                    rrrb.ref(SearchJobPageResult.getTitle());
//                  }));
//                }));
//              }));
//            }));
//          });
//        });
//      });
//    });
//    Backend backend = BackendDocParser.parse(openapi3);
//    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
//    springbootFolderGenerator.generate();
//  }
//
//  @Test
//  public void TestRequestBodyPlainText() throws IOException {
//    Openapi3 openapi3 = openapi3(ob -> {
//      info(ib -> {
//        ib.title("测试");
//        ib.version("v1.0.0");
//      });
//
//      server(sb -> {
//        sb.url("http://www.unionj.com");
//      });
//
//      path("/shortLink", pb -> {
//        post(ppb -> {
//          ppb.summary("获取短链接");
//          ppb.tags("survey");
//
//          requestBody(rb -> {
//            rb.required(true);
//            rb.content(content(cb -> {
//              cb.textPlain(mediaType(mb -> {
//                mb.schema(string);
//              }));
//            }));
//          });
//
//          responses(rb -> {
//            rb.response200(response(rrb -> {
//              rrb.description("OK");
//              rrb.content(content(cb -> {
//                cb.applicationJson(mediaType(mb -> {
//                  mb.schema(reference(rrrb -> {
//                    rrrb.ref(SearchJobPageResult.getTitle());
//                  }));
//                }));
//              }));
//            }));
//          });
//        });
//      });
//    });
//    Backend backend = BackendDocParser.parse(openapi3);
//    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
//    springbootFolderGenerator.generate();
//  }
//
//  @Test
//  public void TestRequestParamDefaultValue() throws IOException {
//    Openapi3 openapi3 = openapi3(ob -> {
//      info(ib -> {
//        ib.title("测试");
//        ib.version("v1.0.0");
//      });
//
//      server(sb -> {
//        sb.url("http://www.unionj.com");
//      });
//
//      path("/shop/info", pb -> {
//        get(ppb -> {
//          ppb.summary("店铺详情");
//          ppb.tags("shop");
//
//          parameter(para -> {
//            para.required(false);
//            para.in("query");
//            para.name("id");
//            para.schema(int32);
//          });
//
//          parameter(para -> {
//            para.required(false);
//            para.in("query");
//            para.name("queryStat");
//            para.description("是否查询统计信息");
//            para.schema(schema(queryStat -> {
//              queryStat.type("boolean");
//              queryStat.defaultValue(false);
//            }));
//          });
//
//          responses(rb -> {
//            rb.response200(response(rrb -> {
//              rrb.content(content(cb -> {
//                cb.applicationJson(mediaType(mb -> {
//                  mb.schema(reference(rrrb -> {
//                    rrrb.ref(SearchJobPageResult.getTitle());
//                  }));
//                }));
//              }));
//            }));
//          });
//        });
//      });
//    });
//    Backend backend = BackendDocParser.parse(openapi3);
//    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
//    springbootFolderGenerator.generate();
//  }
//
//  @Test
//  public void TestParameterOrder() throws IOException {
//    Openapi3 openapi3 = openapi3(ob -> {
//      info(ib -> {
//        ib.title("测试");
//        ib.version("v1.0.0");
//      });
//
//      server(sb -> {
//        sb.url("http://www.unionj.com");
//      });
//
//      path("/shop/info", pb -> {
//        get(ppb -> {
//          ppb.summary("店铺详情");
//          ppb.tags("shop");
//
//          parameter(para -> {
//            para.required(false);
//            para.in("query");
//            para.name("id");
//            para.schema(int32);
//          });
//
//          parameter(para -> {
//            para.required(false);
//            para.in("query");
//            para.name("queryStat");
//            para.description("是否查询统计信息");
//            para.schema(schema(queryStat -> {
//              queryStat.type("boolean");
//              queryStat.defaultValue(false);
//            }));
//          });
//
//          responses(rb -> {
//            rb.response200(response(rrb -> {
//              rrb.content(content(cb -> {
//                cb.applicationJson(mediaType(mb -> {
//                  mb.schema(reference(rrrb -> {
//                    rrrb.ref(SearchJobPageResult.getTitle());
//                  }));
//                }));
//              }));
//            }));
//          });
//        });
//      });
//    });
//    Backend backend = BackendDocParser.parse(openapi3);
//    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
//    springbootFolderGenerator.generate();
//  }
//
//  @Test
//  public void TestParameterOrder2() throws IOException {
//    Openapi3 openapi3 = openapi3(ob -> {
//      info(ib -> {
//        ib.title("测试");
//        ib.version("v1.0.0");
//      });
//
//      Server.server(sb -> {
//        sb.url("http://www.unionj.com");
//      });
//
//      path("/clshenbao/form/save", pb -> {
//        post(ppb -> {
//          ppb.summary("材料申报：保存form表单");
//          ppb.tags("clshenbao");
//          ppb.tags("clshenbaoForm");
//
//          requestBody(rb -> {
//            rb.required(true);
//            rb.content(content(cb -> {
//              cb.applicationJson(mediaType(mb -> {
//                mb.schema(schema(sb -> {
//                  sb.type("object");
//                  sb.properties("userID", int64);
//                  sb.properties("fields", stringArray);
//                }));
//              }));
//            }));
//          });
//
//          responses(rb -> {
//            rb.response200(response(rrb -> {
//              rrb.content(content(cb -> {
//                cb.applicationJson(mediaType(mb -> {
//                  mb.schema(reference(rrrb -> {
//                    rrrb.ref(SearchJobPageResult.getTitle());
//                  }));
//                }));
//              }));
//            }));
//          });
//        });
//      });
//    });
//    Backend backend = BackendDocParser.parse(openapi3);
//    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
//    springbootFolderGenerator.generate();
//  }
//
//  @Test
//  public void generate3() throws IOException {
//    Openapi3 openapi3 = openapi3(ob -> {
//      info(ib -> {
//        ib.title("测试");
//        ib.version("v1.0.0");
//      });
//
//      Server.server(sb -> {
//        sb.url("http://www.unionj.com");
//      });
//
//      path("/oss/get", pb -> {
//        get(ppb -> {
//          ppb.summary("获取附件");
//          ppb.tags("attachment");
//
//          parameter(para -> {
//            para.required(true);
//            para.in("query");
//            para.name("key");
//            para.schema(string);
//          });
//
//          parameter(para -> {
//            para.required(false);
//            para.in("query");
//            para.name("style");
//            para.schema(string);
//          });
//
//          responses(rb -> {
//            rb.response200(response(rrb -> {
//              rrb.content(content(cb -> {
//                cb.applicationOctetStream(mediaType(mb -> {
//                  mb.schema(schema(download -> {
//                    download.type("string");
//                    download.format("binary");
//                  }));
//                }));
//              }));
//            }));
//          });
//        });
//      });
//    });
//
//    Backend backend = BackendDocParser.parse(openapi3);
//    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
//    springbootFolderGenerator.generate();
//  }


  private static Schema ResultVO = schema(sb -> {
    sb.type("object");
    sb.title("ResultVO");
    sb.properties("code", int32);
    sb.properties("msg", string);
    sb.properties("data", T);
  });

  public static Schema UserRegisterFormVO = schema(sb -> {
    sb.type("object");
    sb.title("UserRegisterFormVO");
    sb.description("用户注册表单");
    sb.properties("username", string("用户名"));
    sb.properties("password", string("密码"));
  });

  public static Schema UserRegisterRespVO = schema(sb -> {
    sb.type("object");
    sb.title("UserRegisterRespVO");
    sb.description("用户注册结果");
    sb.properties("id", int64("用户ID"));
  });

  public static Schema ResultVOUserRegisterForm = generic(gb -> {
    gb.generic(ResultVO, ref(UserRegisterRespVO.getTitle()));
  });

  @SneakyThrows
  @Test
  public void TestMultipartFormData() {
    Openapi3 openAPI3 = openapi3(ob -> {
      info(ib -> {
        ib.title("用户管理模块");
        ib.version("v1.0.0");
      });

      server(sb -> {
        sb.url("http://unionj.cloud");
      });

      post("/api/user/register", PathConfig.builder()
          .summary("用户注册接口")
          .tags(new String[]{"用户管理模块", "User"})
          .reqSchema(UserRegisterFormVO)
          .reqSchemaType(PathConfig.SchemaType.FORMDATA)
          .respSchema(ResultVOUserRegisterForm)
          .build());
    });
    Backend backend = BackendDocParser.parse(openAPI3);
    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
    springbootFolderGenerator.generate();
  }

}
