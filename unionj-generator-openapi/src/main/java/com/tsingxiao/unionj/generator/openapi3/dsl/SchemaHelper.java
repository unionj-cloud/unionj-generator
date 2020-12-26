package com.tsingxiao.unionj.generator.openapi3.dsl;

import com.tsingxiao.unionj.generator.openapi3.model.Schema;

import static com.tsingxiao.unionj.generator.openapi3.dsl.Reference.reference;
import static com.tsingxiao.unionj.generator.openapi3.dsl.Schema.schema;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/26
 */
public class SchemaHelper {

  public static Schema int32(String description) {
    return schema(int32 -> {
      int32.type("integer");
      int32.format("int32");
      int32.description(description);
    });
  }

  public static Schema int64(String description) {
    return schema(int64 -> {
      int64.type("integer");
      int64.format("int32");
      int64.description(description);
    });
  }

  public static Schema string(String description) {
    return schema(string -> {
      string.type("string");
      string.description(description);
    });
  }

  public static Schema bool(String description) {
    return schema(string -> {
      string.type("boolean");
      string.description(description);
    });
  }

  public static Schema floatNumber(String description) {
    return schema(floatNumer -> {
      floatNumer.type("number");
      floatNumer.format("float");
      floatNumer.description(description);
    });
  }

  public static Schema doubleNumer(String description) {
    return schema(doubleNumer -> {
      doubleNumer.type("number");
      doubleNumer.format("double");
      doubleNumer.description(description);
    });
  }

  public static Schema array(String description, String ref) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(reference(items -> {
        items.ref(ref);
      }));
    });
  }

}
