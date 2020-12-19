package com.tsingxiao.unionj.generator.openapi3.dsl.paths;

import com.tsingxiao.unionj.generator.openapi3.expression.paths.ResponseBuilder;

import java.util.function.Consumer;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl.paths
 * @date:2020/12/19
 */
public class Response {

  private static ResponseBuilder responseBuilder;

  public static com.tsingxiao.unionj.generator.openapi3.model.paths.Response response(Consumer<ResponseBuilder> consumer) {
    responseBuilder = new ResponseBuilder();
    consumer.accept(responseBuilder);
    com.tsingxiao.unionj.generator.openapi3.model.paths.Response response = responseBuilder.build();
    return response;
  }
}
