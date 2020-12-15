package com.tsingxiao.unionj.generator.openapi3.dsl.info;

import com.tsingxiao.unionj.generator.openapi3.eval.Evaluator;
import com.tsingxiao.unionj.generator.openapi3.expression.info.ContactBuilder;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Contact extends Info {

  private static ContactBuilder contactBuilder;

  public static void contact(Evaluator evaluator) {
    contactBuilder = new ContactBuilder();
    evaluator.eval();
    com.tsingxiao.unionj.generator.openapi3.model.info.Contact contact = contactBuilder.build();
    infoBuilder.contact(contact);
  }

  public static void email(String email) {
    contactBuilder.email(email);
  }
}
