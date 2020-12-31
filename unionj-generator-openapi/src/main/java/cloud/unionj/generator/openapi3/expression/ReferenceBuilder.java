package cloud.unionj.generator.openapi3.expression;

import cloud.unionj.generator.openapi3.model.Schema;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.openapi3.expression
 * @date:2020/12/16
 */
public class ReferenceBuilder {

  private Schema reference;

  public ReferenceBuilder() {
    this.reference = new Schema();
  }

  public void ref(String ref) {
    this.reference.setRef("#/components/schemas/" + ref);
  }

  public Schema build() {
    return this.reference;
  }
}
