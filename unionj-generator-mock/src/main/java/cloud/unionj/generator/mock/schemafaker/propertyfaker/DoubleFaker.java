package cloud.unionj.generator.mock.schemafaker.propertyfaker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.github.javafaker.Faker;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.mock.schemafaker
 *  date 2020/11/20
 */
public class DoubleFaker implements PropertyFaker {

  private Faker faker = new Faker();

  public DoubleFaker(Faker faker) {
    this.faker = faker;
  }

  public DoubleFaker() {
  }

  @Override
  public JsonNode fake() {
    return DoubleNode.valueOf(this.faker.number().randomDouble(2, 0, 100));
  }
}
