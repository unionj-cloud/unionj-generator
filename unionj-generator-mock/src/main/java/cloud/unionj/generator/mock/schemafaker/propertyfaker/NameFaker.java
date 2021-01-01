package cloud.unionj.generator.mock.schemafaker.propertyfaker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.javafaker.Faker;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.mock.schemafaker
 *  date 2020/11/20
 */
public class NameFaker implements PropertyFaker {

  private Faker faker = new Faker();

  public NameFaker(Faker faker) {
    this.faker = faker;
  }

  public NameFaker() {
  }

  @Override
  public JsonNode fake() {
    return TextNode.valueOf(this.faker.name().fullName());
  }
}
