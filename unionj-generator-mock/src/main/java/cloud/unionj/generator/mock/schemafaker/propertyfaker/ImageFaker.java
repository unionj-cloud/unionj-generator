package cloud.unionj.generator.mock.schemafaker.propertyfaker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Base64;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.mock.schemafaker
 *  date 2020/11/20
 */
public class ImageFaker implements PropertyFaker {

  private Faker faker = new Faker();

  public ImageFaker(Faker faker) {
    this.faker = faker;
  }

  public ImageFaker() {
  }

  @SneakyThrows
  @Override
  public JsonNode fake() {
    File imgFolder = new File(ImageFaker.class.getClassLoader().getResource("img").toURI());
    if (imgFolder.isDirectory()) {
      File[] files = imgFolder.listFiles();
      File img = files[this.faker.number().numberBetween(0, files.length)];
      byte[] fileContent = FileUtils.readFileToByteArray(img);
      String encodedString = Base64.getEncoder().encodeToString(fileContent);
      return TextNode.valueOf("data:image/jpeg;charset=utf-8;base64, " + encodedString);
    }
    return TextNode.valueOf(this.faker.internet().image(this.faker.number().numberBetween(200, 400), this.faker.number().numberBetween(100, 400), false, null));
  }
}
