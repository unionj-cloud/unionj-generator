package com.tsingxiao.unionj.generator.openapi3.dsl.paths;

import com.tsingxiao.unionj.generator.openapi3.eval.Evaluator;
import com.tsingxiao.unionj.generator.openapi3.expression.paths.OperationBuilder;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Delete extends Operation {

  public static void delete(Evaluator evaluator) {
    operationBuilder = new OperationBuilder();
    evaluator.eval();
    com.tsingxiao.unionj.generator.openapi3.model.paths.Operation delete = operationBuilder.build();
    pathBuilder.delete(delete);
  }
}
