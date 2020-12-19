package com.tsingxiao.unionj.generator.openapi3.dsl.info;

import com.tsingxiao.unionj.generator.openapi3.dsl.Openapi3;
import com.tsingxiao.unionj.generator.openapi3.expression.info.InfoBuilder;

import java.util.function.Consumer;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Info extends Openapi3 {

  protected static InfoBuilder infoBuilder;

  public static void info(Consumer<InfoBuilder> consumer) {
    infoBuilder = new InfoBuilder();
    consumer.accept(infoBuilder);
    com.tsingxiao.unionj.generator.openapi3.model.info.Info info = infoBuilder.build();
    openapi3Builder.info(info);
  }
}
