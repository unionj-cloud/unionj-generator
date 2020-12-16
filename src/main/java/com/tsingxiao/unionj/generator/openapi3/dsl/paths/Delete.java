package com.tsingxiao.unionj.generator.openapi3.dsl.paths;

import com.tsingxiao.unionj.generator.openapi3.expression.paths.OperationBuilder;

import java.util.function.Consumer;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Delete extends Operation {

  public static void delete(Consumer<OperationBuilder> consumer) {
    operationBuilder = new OperationBuilder();
    consumer.accept(operationBuilder);
    com.tsingxiao.unionj.generator.openapi3.model.paths.Operation delete = operationBuilder.build();
    pathBuilder.delete(delete);
  }
}
