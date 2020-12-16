package com.tsingxiao.unionj.generator.openapi3.dsl.paths;

import com.tsingxiao.unionj.generator.openapi3.expression.paths.ParameterBuilder;
import com.tsingxiao.unionj.generator.openapi3.model.Schema;

import java.util.function.Consumer;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Parameter extends Operation {

  private static ParameterBuilder parameterBuilder;

  public static void parameter(Consumer<ParameterBuilder> consumer) {
    parameterBuilder = new ParameterBuilder();
    consumer.accept(parameterBuilder);
    com.tsingxiao.unionj.generator.openapi3.model.Parameter parameter = parameterBuilder.build();
    operationBuilder.parameters(parameter);
  }
}
