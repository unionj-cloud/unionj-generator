package cloud.unionj.generator.openapi3;

import cloud.unionj.generator.openapi3.dsl.paths.Delete;
import cloud.unionj.generator.openapi3.dsl.paths.Get;
import cloud.unionj.generator.openapi3.dsl.paths.Post;
import cloud.unionj.generator.openapi3.dsl.paths.Put;
import cloud.unionj.generator.openapi3.expression.paths.OperationBuilder;
import cloud.unionj.generator.openapi3.expression.paths.ResponsesBuilder;
import cloud.unionj.generator.openapi3.model.Schema;
import cloud.unionj.generator.openapi3.model.paths.Parameter;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Consumer;

import static cloud.unionj.generator.openapi3.dsl.Schema.schema;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.*;
import static cloud.unionj.generator.openapi3.dsl.paths.Content.content;
import static cloud.unionj.generator.openapi3.dsl.paths.MediaType.mediaType;
import static cloud.unionj.generator.openapi3.dsl.paths.Parameter.parameter;
import static cloud.unionj.generator.openapi3.dsl.paths.Path.path;
import static cloud.unionj.generator.openapi3.dsl.paths.RequestBody.requestBody;
import static cloud.unionj.generator.openapi3.dsl.paths.Response.response;
import static cloud.unionj.generator.openapi3.dsl.paths.Responses.responses;

/**
 * @author created by wubin
 * @version v0.0.1
 * description: cloud.unionj.generator.openapi3
 * date:2021/5/11
 */
public class PathHelper {

  private static void errorResponse(ResponsesBuilder rb) {
    rb.response403(response(rrb -> {
      rrb.description("Forbidden");
      rrb.content(content(cb -> {
        cb.applicationJson(mediaType(mb -> {
          mb.schema(schema(sb -> {
            sb.type("object");
            sb.properties("code", int32);
            sb.properties("msg", string);
            sb.properties("data", string);
          }));
        }));
      }));
    }));
    rb.response401(response(rrb -> {
      rrb.description("Unauthorized");
      rrb.content(content(cb -> {
        cb.applicationJson(mediaType(mb -> {
          mb.schema(schema(sb -> {
            sb.type("object");
            sb.properties("code", int32);
            sb.properties("msg", string);
            sb.properties("data", string);
          }));
        }));
      }));
    }));
  }

  private static Consumer<OperationBuilder> configure(PathConfig config) {
    return ppb -> {
      ppb.summary(config.getSummary());
      if (config.getTags() != null) {
        for (String tag : config.getTags()) {
          ppb.tags(tag);
        }
      }
      if (config.getParameters() != null) {
        for (Parameter item : config.getParameters()) {
          parameter(item);
        }
      }

      if (config.getReqSchema() != null) {
        PathConfig.SchemaType schemaType = config.getReqSchemaType();
        if (schemaType == null) {
          schemaType = PathConfig.SchemaType.JSON;
        }
        switch (schemaType) {
          case FORMDATA: {
            requestBody(rb -> {
              rb.required(true);
              rb.content(content(cb -> {
                cb.multipartFormData(mediaType(mb -> {
                  if (StringUtils.isEmpty(config.getReqSchema().getxTitle())) {
                    mb.schema(config.getReqSchema());
                  } else {
                    mb.schema(ref(config.getReqSchema().getxTitle()));
                  }
                }));
              }));
            });
            break;
          }
          default: {
            requestBody(rb -> {
              rb.required(true);
              rb.content(content(cb -> {
                cb.applicationJson(mediaType(mb -> {
                  if (StringUtils.isEmpty(config.getReqSchema().getxTitle())) {
                    mb.schema(config.getReqSchema());
                  } else {
                    mb.schema(ref(config.getReqSchema().getxTitle()));
                  }
                }));
              }));
            });
          }
        }
      }

      PathConfig.SchemaType schemaType = config.getRespSchemaType();
      if (schemaType == null) {
        Schema respSchema = config.getRespSchema();
        if (respSchema.equals(file)) {
          schemaType = PathConfig.SchemaType.STREAM;
        } else {
          schemaType = PathConfig.SchemaType.JSON;
        }
      }
      switch (schemaType) {
        case STREAM: {
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
            errorResponse(rb);
          });
          break;
        }
        default: {
          responses(rb -> {
            rb.response200(response(rrb -> {
              rrb.content(content(cb -> {
                cb.applicationJson(mediaType(mb -> {
                  if (StringUtils.isEmpty(config.getRespSchema().getxTitle())) {
                    mb.schema(config.getRespSchema());
                  } else {
                    mb.schema(ref(config.getRespSchema().getxTitle()));
                  }
                }));
              }));
            }));
            errorResponse(rb);
          });
        }
      }
    };
  }

  public static void get(String endpoint, PathConfig config) {
    path(endpoint, pb -> {
      Get.get(configure(config));
    });
  }

  public static void post(String endpoint, PathConfig config) {
    path(endpoint, pb -> {
      Post.post(configure(config));
    });
  }

  public static void put(String endpoint, PathConfig config) {
    path(endpoint, pb -> {
      Put.put(configure(config));
    });
  }

  public static void delete(String endpoint, PathConfig config) {
    path(endpoint, pb -> {
      Delete.delete(configure(config));
    });
  }

}
