package com.tsingxiao.unionj.generator.openapi3.dsl.paths;

import com.tsingxiao.unionj.generator.openapi3.expression.paths.OperationBuilder;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Operation extends Path {

  protected static OperationBuilder operationBuilder;

  public static void summary(String summary) {
    operationBuilder.summary(summary);
  }

  public static void description(String description) {
    operationBuilder.description(description);
  }

  public static void operationId(String operationId) {
    operationBuilder.operationId(operationId);
  }

  public static void deprecated(boolean deprecated) {
    operationBuilder.deprecated(deprecated);
  }

  public static void tags(String tag) {
    operationBuilder.tags(tag);
  }
}
