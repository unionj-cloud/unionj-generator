package cloud.unionj.generator.openapi3.dsl.paths;

import cloud.unionj.generator.openapi3.expression.paths.OperationBuilder;

import java.util.function.Consumer;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.dsl
 *  date 2020/12/14
 */
public class Get extends Operation {

  public static void get(Consumer<OperationBuilder> consumer) {
    operationBuilder = new OperationBuilder();
    consumer.accept(operationBuilder);
    cloud.unionj.generator.openapi3.model.paths.Operation get = operationBuilder.build();
    pathBuilder.get(get);
  }
}
