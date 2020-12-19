package com.tsingxiao.unionj.generator.openapi3.dsl.paths;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tsingxiao.unionj.generator.openapi3.model.Openapi3;
import org.junit.Test;

import static com.tsingxiao.unionj.generator.openapi3.dsl.Openapi3.openapi3;
import static com.tsingxiao.unionj.generator.openapi3.dsl.Reference.reference;
import static com.tsingxiao.unionj.generator.openapi3.dsl.Schema.schema;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.Components.importComponents;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.Content.content;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.Get.get;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.MediaType.mediaType;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.Parameter.parameter;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.Path.path;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.Post.post;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.RequestBody.requestBody;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.Response.response;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.Responses.responses;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl.paths
 * @date:2020/12/16
 */
public class PathTest {

  @Test
  public void TestPath() throws JsonProcessingException {
    Openapi3 openapi3 = openapi3(ob -> {

      importComponents();

      path("/pet/findByStatus", pb -> {
        get(gb -> {
          gb.summary("Finds Pets by status");
          gb.description("Multiple status values can be provided with comma separated strings");
          gb.operationId("findPetsByStatus");
          gb.tags("pet");

          parameter(ppb -> {
            ppb.description("Status values that need to be considered for filter");
            ppb.name("status");
            ppb.in("query");
            ppb.schema(schema(sb -> {
              sb.defaultValue("available");
              sb.type("string");
              sb.enumValue("available");
              sb.enumValue("pending");
              sb.enumValue("sold");
            }));
          });

          responses(rb -> {
            rb.response200(response(rrb -> {
              rrb.description("successful operation");
              rrb.content(content(cb -> {
                cb.applicationJson(mediaType(mb -> {
                  mb.schema(schema(sb -> {
                    sb.type("array");
                    sb.items(reference(rrrb -> {
                      rrrb.ref("Pet");
                    }));
                  }));
                }));
              }));
            }));

            rb.response400(response(rrb -> {
              rrb.description("Invalid status value");
            }));
          });
        });
      });

      path("/pet", pb -> {
        post(ppb -> {
          ppb.description("Add a new pet to the store");
          ppb.summary("Add a new pet to the store");
          ppb.operationId("addPet");
          ppb.tags("pet");

          requestBody(rb -> {
            rb.description("Add a new pet to the store");
            rb.required(true);
            rb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrb -> {
                  rrb.ref("Pet");
                }));
              }));
              cb.applicationXWwwFormUrlencoded(mediaType(mb -> {
                mb.schema(reference(rrb -> {
                  rrb.ref("Pet");
                }));
              }));
            }));
          });

          responses(rb -> {
            rb.response200(response(rrb -> {
              rrb.description("successful operation");
              rrb.content(content(cb -> {
                cb.applicationJson(mediaType(mb -> {
                  mb.schema(reference(rrrb -> {
                    rrrb.ref("Pet");
                  }));
                }));
              }));
            }));

            rb.response405(response(rrb -> {
              rrb.description("Invalid input");
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
