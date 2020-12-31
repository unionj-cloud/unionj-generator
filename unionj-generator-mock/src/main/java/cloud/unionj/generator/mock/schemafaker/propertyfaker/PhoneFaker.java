package cloud.unionj.generator.mock.schemafaker.propertyfaker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.javafaker.Faker;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.mock.schemafaker
 * @date:2020/11/20
 */
public class PhoneFaker implements PropertyFaker {

  private Faker faker = new Faker();

  public PhoneFaker(Faker faker) {
    this.faker = faker;
  }

  public PhoneFaker() {
  }

  @Override
  public JsonNode fake() {
    return TextNode.valueOf(this.faker.phoneNumber().cellPhone());
  }
}
