package com.tsingxiao.unionj.generator.openapi3.dsl.info;

import com.tsingxiao.unionj.generator.openapi3.dsl.Openapi3;
import com.tsingxiao.unionj.generator.openapi3.eval.Evaluator;
import com.tsingxiao.unionj.generator.openapi3.expression.info.InfoBuilder;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Info extends Openapi3 {

  protected static InfoBuilder infoBuilder;

  public static void info(Evaluator evaluator) {
    infoBuilder = new InfoBuilder();
    evaluator.eval();
    com.tsingxiao.unionj.generator.openapi3.model.info.Info info = infoBuilder.build();
    openapi3Builder.info(info);
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
