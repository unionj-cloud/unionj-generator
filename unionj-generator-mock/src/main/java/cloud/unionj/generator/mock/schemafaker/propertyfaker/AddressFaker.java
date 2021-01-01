package cloud.unionj.generator.mock.schemafaker.propertyfaker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.javafaker.Faker;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.mock.schemafaker
 * @date 2020/11/20
 */
public class AddressFaker implements PropertyFaker {

  private Faker faker = new Faker();

  public AddressFaker(Faker faker) {
    this.faker = faker;
  }

  public AddressFaker() {
  }

  @Override
  public JsonNode fake() {
    return TextNode.valueOf(this.faker.address().cityName() + this.faker.address().streetAddress() + this.faker.address().buildingNumber());
  }
}
