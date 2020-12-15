package com.tsingxiao.unionj.generator.openapi3.expression;

import com.tsingxiao.unionj.generator.openapi3.model.info.Contact;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.expression
 * @date:2020/12/14
 */
public class ContactBuilder {

  private Contact contact;

  public ContactBuilder() {
    this.contact = new Contact();
  }

  public void email(String email) {
    this.contact.setEmail(email);
  }

  public Contact build() {
    return this.contact;
  }
}
