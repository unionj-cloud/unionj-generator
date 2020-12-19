package com.tsingxiao.unionj.generator.openapi3.dsl.components;

import com.tsingxiao.unionj.generator.openapi3.dsl.Openapi3;
import com.tsingxiao.unionj.generator.openapi3.expression.components.ComponentsBuilder;

import java.util.function.Consumer;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl.components
 * @date:2020/12/16
 */
public class Components extends Openapi3 {

  protected static ComponentsBuilder componentsBuilder;

  public static void components(Consumer<ComponentsBuilder> consumer) {
    componentsBuilder = new ComponentsBuilder();
    consumer.accept(componentsBuilder);
    com.tsingxiao.unionj.generator.openapi3.model.Components components = componentsBuilder.build();
    openapi3Builder.components(components);
  }
}
