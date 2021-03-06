package cloud.unionj.generator.openapi3.expression;

import cloud.unionj.generator.openapi3.dsl.IGeneric;
import cloud.unionj.generator.openapi3.model.Generic;
import cloud.unionj.generator.openapi3.model.Schema;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.expression
 * date 2020/12/16
 */
public class GenericBuilder {

  protected Generic generic;

  public GenericBuilder() {
  }

  public void generic(IGeneric iGeneric, Schema element) {
    this.generic = iGeneric.generic(element);
  }

  public Generic build() {
    return this.generic;
  }
}
