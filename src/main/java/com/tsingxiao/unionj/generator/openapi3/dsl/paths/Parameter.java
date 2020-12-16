package com.tsingxiao.unionj.generator.openapi3.dsl.paths;

import com.tsingxiao.unionj.generator.openapi3.eval.Evaluator;
import com.tsingxiao.unionj.generator.openapi3.expression.paths.ParameterBuilder;
import com.tsingxiao.unionj.generator.openapi3.model.Schema;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Parameter extends Operation {

  private static ParameterBuilder parameterBuilder;

  public static void parameter(Evaluator evaluator) {
    parameterBuilder = new ParameterBuilder();
    evaluator.eval();
    com.tsingxiao.unionj.generator.openapi3.model.Parameter parameter = parameterBuilder.build();
    operationBuilder.parameters(parameter);
  }

  public static void name(String name) {
    parameterBuilder.name(name);
  }

  public static void in(String in) {
    parameterBuilder.in(in);
  }

  public static void description(String description) {
    parameterBuilder.description(description);
  }

  public static void required(boolean required) {
    parameterBuilder.required(required);
  }

  public static void deprecated(boolean deprecated) {
    parameterBuilder.deprecated(deprecated);
  }

  public static void example(Object example) {
    parameterBuilder.example(example);
  }

  public static void schema(Schema schema) {
    parameterBuilder.schema(schema);
  }
}
