package com.tsingxiao.unionj.generator.openapi3.expression;


import com.tsingxiao.unionj.generator.openapi3.model.Contact;
import com.tsingxiao.unionj.generator.openapi3.model.Info;
import com.tsingxiao.unionj.generator.openapi3.model.License;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.expression
 * @date:2020/12/14
 */
public class InfoBuilder {

  private Info info;

  public InfoBuilder() {
    this.info = new Info();
  }

  public void title(String title) {
    this.info.setTitle(title);
  }

  public void description(String description) {
    this.info.setDescription(description);
  }

  public void termsOfService(String termsOfService) {
    this.info.setTermsOfService(termsOfService);
  }

  public void version(String version) {
    this.info.setVersion(version);
  }

  public void contact(Contact contact) {
    this.info.setContact(contact);
  }

  public void license(License license) {
    this.info.setLicense(license);
  }

  public Info build() {
    return this.info;
  }

}
