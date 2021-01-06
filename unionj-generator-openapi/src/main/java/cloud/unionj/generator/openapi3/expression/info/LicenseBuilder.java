package cloud.unionj.generator.openapi3.expression.info;

import cloud.unionj.generator.openapi3.model.info.License;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.expression
 *  date 2020/12/14
 */
public class LicenseBuilder {

  private License license;

  public LicenseBuilder() {
    this.license = new License();
  }

  public void name(String name) {
    this.license.setName(name);
  }

  public void url(String url) {
    this.license.setUrl(url);
  }

  public License build() {
    return this.license;
  }
}
