package com.tsingxiao.unionj.generator.openapi3.dsl.paths;

import com.tsingxiao.unionj.generator.openapi3.dsl.Openapi3;
import com.tsingxiao.unionj.generator.openapi3.eval.Evaluator;
import com.tsingxiao.unionj.generator.openapi3.expression.paths.PathBuilder;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Path extends Openapi3 {

  protected static PathBuilder pathBuilder;

  public static void path(Evaluator evaluator) {
    pathBuilder = new PathBuilder();
    evaluator.eval();
    com.tsingxiao.unionj.generator.openapi3.model.paths.Path path = pathBuilder.build();
    openapi3Builder.paths(path);
  }

  public static void endpoint(String name) {
    pathBuilder.endpoint(name);
  }

}
