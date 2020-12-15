package com.tsingxiao.unionj.generator.openapi3.dsl.tags;

import com.tsingxiao.unionj.generator.openapi3.dsl.Openapi3;
import com.tsingxiao.unionj.generator.openapi3.eval.Evaluator;
import com.tsingxiao.unionj.generator.openapi3.expression.TagBuilder;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Tag extends Openapi3 {

  protected static TagBuilder tagBuilder;

  public static void server(Evaluator evaluator) {
    tagBuilder = new TagBuilder();
    evaluator.eval();
    com.tsingxiao.unionj.generator.openapi3.model.tags.Tag tag = tagBuilder.build();
    openapi3Builder.tags(tag);
  }

  public static void name(String name) {
    tagBuilder.name(name);
  }

  public static void description(String description) {
    tagBuilder.description(description);
  }
}
