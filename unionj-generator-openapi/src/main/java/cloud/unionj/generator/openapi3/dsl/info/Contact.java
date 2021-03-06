package cloud.unionj.generator.openapi3.dsl.info;

import cloud.unionj.generator.openapi3.expression.info.ContactBuilder;

import java.util.function.Consumer;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.dsl
 * date 2020/12/14
 */
public class Contact extends Info {

  public static void contact(Consumer<ContactBuilder> consumer) {
    ContactBuilder contactBuilder = new ContactBuilder();
    consumer.accept(contactBuilder);
    cloud.unionj.generator.openapi3.model.info.Contact contact = contactBuilder.build();
    infoBuilder.contact(contact);
  }
}
