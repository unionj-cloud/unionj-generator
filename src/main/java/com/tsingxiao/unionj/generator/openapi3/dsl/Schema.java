package com.tsingxiao.unionj.generator.openapi3.dsl;

import com.tsingxiao.unionj.generator.openapi3.eval.Evaluator;
import com.tsingxiao.unionj.generator.openapi3.expression.SchemaBuilder;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/16
 */
public class Schema {

  private static SchemaBuilder schemaBuilder;

  public static com.tsingxiao.unionj.generator.openapi3.model.Schema Schema(Evaluator evaluator) {
    schemaBuilder = new SchemaBuilder();
    evaluator.eval();
    com.tsingxiao.unionj.generator.openapi3.model.Schema schema = schemaBuilder.build();
    return schema;
  }

}
