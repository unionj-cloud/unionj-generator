package com.tsingxiao.unionj.generator.openapi3.dsl;

import com.tsingxiao.unionj.generator.openapi3.expression.GenericBuilder;

import java.util.function.Consumer;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/16
 */
public class Generic {

  protected static GenericBuilder genericBuilder;

  public static com.tsingxiao.unionj.generator.openapi3.model.Generic generic(Consumer<GenericBuilder> consumer) {
    genericBuilder = new GenericBuilder();
    consumer.accept(genericBuilder);
    com.tsingxiao.unionj.generator.openapi3.model.Generic schema = genericBuilder.build();
    return schema;
  }
}
