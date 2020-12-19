package com.tsingxiao.unionj.generator.openapi3.dsl;

import com.tsingxiao.unionj.generator.openapi3.expression.ReferenceBuilder;
import com.tsingxiao.unionj.generator.openapi3.model.Schema;

import java.util.function.Consumer;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/16
 */
public class Reference {

  protected static ReferenceBuilder referenceBuilder;

  public static Schema reference(Consumer<ReferenceBuilder> consumer) {
    referenceBuilder = new ReferenceBuilder();
    consumer.accept(referenceBuilder);
    Schema schema = referenceBuilder.build();
    return schema;
  }
}
