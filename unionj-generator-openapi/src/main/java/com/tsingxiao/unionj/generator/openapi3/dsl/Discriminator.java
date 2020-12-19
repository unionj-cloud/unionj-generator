package com.tsingxiao.unionj.generator.openapi3.dsl;

import com.tsingxiao.unionj.generator.openapi3.expression.DiscriminatorBuilder;

import java.util.function.Consumer;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/16
 */
public class Discriminator {

  public static com.tsingxiao.unionj.generator.openapi3.model.Discriminator discriminator(Consumer<DiscriminatorBuilder> consumer) {
    DiscriminatorBuilder discriminatorBuilder = new DiscriminatorBuilder();
    consumer.accept(discriminatorBuilder);
    com.tsingxiao.unionj.generator.openapi3.model.Discriminator discriminator = discriminatorBuilder.build();
    return discriminator;
  }
}
