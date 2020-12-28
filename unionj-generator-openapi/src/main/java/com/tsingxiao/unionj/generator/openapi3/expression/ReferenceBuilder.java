package com.tsingxiao.unionj.generator.openapi3.expression;

import com.tsingxiao.unionj.generator.openapi3.model.Schema;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.expression
 * @date:2020/12/16
 */
public class ReferenceBuilder {

  private Schema reference;

  public ReferenceBuilder() {
    this.reference = new Schema();
  }

  public void ref(String ref) {
    ref = ref.replaceAll("[^a-zA-Z]", "");
    this.reference.setRef("#/components/schemas/" + ref);
  }

  public Schema build() {
    return this.reference;
  }
}
