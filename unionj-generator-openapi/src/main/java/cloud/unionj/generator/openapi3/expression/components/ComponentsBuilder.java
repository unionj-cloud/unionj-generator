package cloud.unionj.generator.openapi3.expression.components;

import cloud.unionj.generator.openapi3.model.Components;
import cloud.unionj.generator.openapi3.model.Schema;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.expression
 *  date 2020/12/16
 */
public class ComponentsBuilder {

  private Components components;

  public ComponentsBuilder() {
    this.components = new Components();
  }

  public void schemas(String key, Schema schema) {
    this.components.schemas(key, schema);
  }

  public Components build() {
    return this.components;
  }

}