package cloud.unionj.generator.openapi3.dsl.paths;

import cloud.unionj.generator.openapi3.expression.paths.ParameterBuilder;

import java.util.function.Consumer;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.dsl
 * date 2020/12/14
 */
public class Parameter extends Operation {

  public static void parameter(Consumer<ParameterBuilder> consumer) {
    ParameterBuilder parameterBuilder = new ParameterBuilder();
    consumer.accept(parameterBuilder);
    cloud.unionj.generator.openapi3.model.paths.Parameter parameter = parameterBuilder.build();
    operationBuilder.parameters(parameter);
  }
}
