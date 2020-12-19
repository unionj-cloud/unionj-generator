package com.tsingxiao.unionj.generator.openapi3.dsl.paths;

import com.tsingxiao.unionj.generator.openapi3.expression.paths.ResponsesBuilder;

import java.util.function.Consumer;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl.paths
 * @date:2020/12/19
 */
public class Responses extends Operation {

  private static ResponsesBuilder responsesBuilder;

  public static void responses(Consumer<ResponsesBuilder> consumer) {
    responsesBuilder = new ResponsesBuilder();
    consumer.accept(responsesBuilder);
    com.tsingxiao.unionj.generator.openapi3.model.paths.Responses responses = responsesBuilder.build();
    operationBuilder.responses(responses);
  }
}
