package cloud.unionj.generator.openapi3.dsl.paths;

import cloud.unionj.generator.openapi3.expression.paths.RequestBodyBuilder;

import java.util.function.Consumer;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.dsl
 * @date 2020/12/14
 */
public class RequestBody extends Operation {

  private static RequestBodyBuilder requestBodyBuilder;

  public static void requestBody(Consumer<RequestBodyBuilder> consumer) {
    requestBodyBuilder = new RequestBodyBuilder();
    consumer.accept(requestBodyBuilder);
    cloud.unionj.generator.openapi3.model.paths.RequestBody requestBody = requestBodyBuilder.build();
    operationBuilder.requestBody(requestBody);
  }

}
