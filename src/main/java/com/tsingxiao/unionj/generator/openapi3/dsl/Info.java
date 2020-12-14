package com.tsingxiao.unionj.generator.openapi3.dsl;

import com.tsingxiao.unionj.generator.openapi3.expression.InfoBuilder;

import java.util.function.Consumer;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Info {

  public static com.tsingxiao.unionj.generator.openapi3.model.Info info(Consumer<InfoBuilder> infoBuilderConsumer) {
    InfoBuilder infoBuilder = new InfoBuilder();
    infoBuilderConsumer.accept(infoBuilder);
    return infoBuilder.build();
  }
}
