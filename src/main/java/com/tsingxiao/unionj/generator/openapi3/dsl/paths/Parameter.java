package com.tsingxiao.unionj.generator.openapi3.dsl.paths;

import com.tsingxiao.unionj.generator.openapi3.eval.Evaluator;
import com.tsingxiao.unionj.generator.openapi3.expression.paths.OperationBuilder;
import com.tsingxiao.unionj.generator.openapi3.expression.paths.ParameterBuilder;

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
    com.tsingxiao.unionj.generator.openapi3.model.paths.Operation build = parameterBuilder.build();
    pathBuilder.get(get);
  }
}
