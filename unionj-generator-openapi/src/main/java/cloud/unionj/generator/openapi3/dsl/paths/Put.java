package cloud.unionj.generator.openapi3.dsl.paths;

import cloud.unionj.generator.openapi3.expression.paths.OperationBuilder;

import java.util.function.Consumer;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Put extends Operation {

  public static void put(Consumer<OperationBuilder> consumer) {
    operationBuilder = new OperationBuilder();
    consumer.accept(operationBuilder);
    cloud.unionj.generator.openapi3.model.paths.Operation put = operationBuilder.build();
    pathBuilder.put(put);
  }
}
