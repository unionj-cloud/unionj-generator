package com.tsingxiao.unionj.generator.openapi3.expression;

import com.tsingxiao.unionj.generator.openapi3.model.Reference;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.expression
 * @date:2020/12/16
 */
public class ReferenceBuilder {

  private Reference reference;

  public ReferenceBuilder() {
    this.reference = new Reference();
  }

  public void ref(String ref) {
    this.reference.setRef("#/components/schemas/" + ref);
  }

  public Reference build() {
    return this.reference;
  }
}
