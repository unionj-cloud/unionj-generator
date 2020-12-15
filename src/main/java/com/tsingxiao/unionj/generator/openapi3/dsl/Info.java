package com.tsingxiao.unionj.generator.openapi3.dsl;

import com.tsingxiao.unionj.generator.openapi3.eval.Evaluator;
import com.tsingxiao.unionj.generator.openapi3.expression.InfoBuilder;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Info {

  protected static InfoBuilder infoBuilder;

  public static com.tsingxiao.unionj.generator.openapi3.model.Info info(Evaluator evaluator) {
    infoBuilder = new InfoBuilder();
    evaluator.eval();
    return infoBuilder.build();
  }

  public static void title(String title) {
    infoBuilder.title(title);
  }

  public static void description(String description) {
    infoBuilder.description(description);
  }

  public static void termsOfService(String termsOfService) {
    infoBuilder.termsOfService(termsOfService);
  }

  public static void version(String version) {
    infoBuilder.version(version);
  }
}
