package cloud.unionj.generator.openapi3.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.model
 *  date 2020/12/19
 */
public class Openapi3Test {

  public static Schema MAGIC = new Schema();
  private static String privateMagic = "privateMagic";

  @Test
  public void parse() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    try (InputStream is = ClassLoader.getSystemResourceAsStream("petstore3.json")) {
      Openapi3 openapi3 = objectMapper.readValue(is, Openapi3.class);
      System.out.println(openapi3);
    }
  }

  @Test
  public void reflectionTest() throws IllegalAccessException {
    Field[] declaredFields = Openapi3Test.class.getDeclaredFields();
    List<Field> staticFields = new ArrayList<>();
    for (Field field : declaredFields) {
      if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) && java.lang.reflect.Modifier.isPublic(field.getModifiers())) {
        staticFields.add(field);
      }
    }
    System.out.println(staticFields.get(0).get(null));
  }
}
