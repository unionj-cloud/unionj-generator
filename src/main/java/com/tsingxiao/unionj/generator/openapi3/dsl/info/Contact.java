package com.tsingxiao.unionj.generator.openapi3.dsl.info;

import com.tsingxiao.unionj.generator.openapi3.expression.info.ContactBuilder;

import java.util.function.Consumer;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Contact extends Info {

  private static ContactBuilder contactBuilder;

  public static void contact(Consumer<ContactBuilder> consumer) {
    contactBuilder = new ContactBuilder();
    consumer.accept(contactBuilder);
    com.tsingxiao.unionj.generator.openapi3.model.info.Contact contact = contactBuilder.build();
    infoBuilder.contact(contact);
  }
}
