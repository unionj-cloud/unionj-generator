package com.tsingxiao.unionj.generator.openapi3.dsl.paths;

import static com.tsingxiao.unionj.generator.openapi3.dsl.Reference.reference;
import static com.tsingxiao.unionj.generator.openapi3.dsl.Schema.schema;
import static com.tsingxiao.unionj.generator.openapi3.dsl.components.Components.components;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl.paths
 * @date:2020/12/19
 */
public class Components {

  public static void importComponents() {
    components(cb -> {
      cb.schemas("Pet", schema(sb -> {
        sb.required("name");
        sb.required("photoUrls");
        sb.type("object");
        sb.properties("id", schema(id -> {
          id.type("integer");
          id.format("int64");
          id.example(10);
        }));
        sb.properties("name", schema(name -> {
          name.type("string");
          name.format("dog");
          name.example("doggie");
        }));
        sb.properties("category", reference(category -> {
          category.ref("Category");
        }));
        sb.properties("photoUrls", schema(photoUrls -> {
          photoUrls.type("array");
          photoUrls.items(schema(ib -> {
            ib.type("string");
            ib.format("image");
          }));
        }));
        sb.properties("tags", schema(tags -> {
          tags.type("array");
          tags.items(reference(rb -> {
            rb.ref("Tag");
          }));
        }));
        sb.properties("status", schema(status -> {
          status.type("string");
          status.description("pet status in the store");
          status.enumValue("available");
          status.enumValue("pending");
          status.enumValue("sold");
        }));
      }));
    });
  }
}
