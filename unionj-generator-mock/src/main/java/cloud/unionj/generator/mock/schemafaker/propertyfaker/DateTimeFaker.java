package cloud.unionj.generator.mock.schemafaker.propertyfaker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.javafaker.Faker;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.mock.schemafaker
 * @date 2020/11/20
 */
public class DateTimeFaker implements PropertyFaker {

  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").withZone(DateTimeZone.forID("Asia/Shanghai"));
  private Faker faker = new Faker();

  public DateTimeFaker(Faker faker) {
    this.faker = faker;
  }

  public DateTimeFaker() {
  }

  @Override
  public JsonNode fake() {
    Date past = this.faker.date().past(60, 1, TimeUnit.DAYS);
    return TextNode.valueOf(dateTimeFormatter.print(past.getTime()));
  }
}
