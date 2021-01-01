package cloud.unionj.generator.openapi3.expression;

import cloud.unionj.generator.openapi3.model.Discriminator;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.expression
 *  date 2020/12/16
 */
public class DiscriminatorBuilder {

  private Discriminator discriminator;

  public void propertyName(String propertyName) {
    this.discriminator.setPropertyName(propertyName);
  }

  public void mapping(String key, String value) {
    this.discriminator.setMapping(key, value);
  }

  public Discriminator build() {
    return this.discriminator;
  }
}
