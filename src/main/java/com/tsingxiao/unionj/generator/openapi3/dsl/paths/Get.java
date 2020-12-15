package com.tsingxiao.unionj.generator.openapi3.dsl.paths;

import com.tsingxiao.unionj.generator.openapi3.dsl.info.Info;
import com.tsingxiao.unionj.generator.openapi3.eval.Evaluator;
import com.tsingxiao.unionj.generator.openapi3.expression.ContactBuilder;
import com.tsingxiao.unionj.generator.openapi3.model.info.Contact;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Get extends Info {

  private static ContactBuilder contactBuilder;

  public static void contact(Evaluator evaluator) {
    contactBuilder = new ContactBuilder();
    evaluator.eval();
    Contact contact = contactBuilder.build();
    infoBuilder.contact(contact);
  }

  public static void email(String email) {
    contactBuilder.email(email);
  }
}
