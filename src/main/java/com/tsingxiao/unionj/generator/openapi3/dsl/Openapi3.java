package com.tsingxiao.unionj.generator.openapi3.dsl;

import com.tsingxiao.unionj.generator.openapi3.eval.Evaluator;
import com.tsingxiao.unionj.generator.openapi3.expression.Openapi3Builder;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/15
 */
public class Openapi3 {

  protected static Openapi3Builder openapi3Builder;

  public static com.tsingxiao.unionj.generator.openapi3.model.Openapi3 openapi3(Evaluator evaluator) {
    openapi3Builder = new Openapi3Builder();
    evaluator.eval();
    return openapi3Builder.build();
  }
}
