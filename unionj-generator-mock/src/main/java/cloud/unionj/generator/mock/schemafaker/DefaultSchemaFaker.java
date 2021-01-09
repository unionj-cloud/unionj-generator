package cloud.unionj.generator.mock.schemafaker;

import cloud.unionj.generator.mock.schemafaker.propertyfaker.Faker;
import cloud.unionj.generator.mock.schemafaker.propertyfaker.FakerNotFoundException;
import cloud.unionj.generator.mock.schemafaker.propertyfaker.FormatConstants;
import cloud.unionj.generator.mock.schemafaker.propertyfaker.PropertyFaker;
import cloud.unionj.generator.openapi3.model.Schema;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.mock.schemafaker
 * date 2020/11/19
 */
@Data
public class DefaultSchemaFaker implements SchemaFaker {

  private com.github.javafaker.Faker faker = new com.github.javafaker.Faker(Locale.SIMPLIFIED_CHINESE);
  private ObjectMapper objectMapper = new ObjectMapper();
  private Map<String, Schema> schemas;


  public DefaultSchemaFaker(com.github.javafaker.Faker faker) {
    this.faker = faker;
  }

  public DefaultSchemaFaker() {
  }

  @SneakyThrows
  @Override
  public JsonNode fakeFormat(String format) {
    FormatConstants formatConstants = FormatConstants.fromFormat(format);
    Faker faker = formatConstants.getClass().getField(formatConstants.name()).getAnnotation(Faker.class);
    if (faker == null) {
      throw new FakerNotFoundException(format + " has no faker found");
    }
    Object concreteFaker = faker.value().getConstructor(com.github.javafaker.Faker.class).newInstance(this.faker);
    PropertyFaker propertyFaker = (PropertyFaker) concreteFaker;
    return propertyFaker.fake();
  }

  @Override
  public JsonNode fakeEnum(List<String> enums) {
    int i = this.faker.number().numberBetween(0, enums.size());
    TextNode jsonNode = TextNode.valueOf(enums.get(i));
    return jsonNode;
  }

  @Override
  public JsonNode fakePrimitiveType(String type) {
    JsonNode jsonNode = null;
    switch (type) {
      case "boolean": {
        jsonNode = BooleanNode.valueOf(this.faker.bool().bool());
        break;
      }
      case "integer": {
        jsonNode = IntNode.valueOf(this.faker.random().nextInt(0, 10000));
        break;
      }
      case "number": {
        jsonNode = DoubleNode.valueOf(this.faker.random().nextDouble());
        break;
      }
      default: {
        jsonNode = TextNode.valueOf(this.faker.lorem().sentence());
        break;
      }
    }
    return jsonNode;
  }

  private JsonNode convertSchema2JsonNode(Schema value) {
    if (value == null || value.isTree()) {
      return null;
    }
    JsonNode result = null;
    String format = value.getFormat();
    List<String> enums = value.getEnumValue();
    String type = value.getType();
    if (StringUtils.isNotBlank(format)) {
      result = this.fakeFormat(format);
    } else if (CollectionUtils.isNotEmpty(enums) && type.equals("string")) {
      result = this.fakeEnum(enums);
    } else {
      if (StringUtils.isNotBlank(value.getRef())) {
        result = this.convertSchema2JsonNode(this.getSchemaByRef(value.getRef()));
      } else {
        if (StringUtils.isNotBlank(type)) {
          if (type.equals("array")) {
            Schema items = value.getItems();
            ArrayNode arrayNode = this.objectMapper.createArrayNode();
            for (int i = 0; i < 15; i++) {
              arrayNode.add(this.convertSchema2JsonNode(items));
            }
            result = arrayNode;
          } else if (type.equals("object")) {
            ObjectNode rootObjectNode = this.objectMapper.createObjectNode();
            Map<String, Schema> properties = value.getProperties();
            if (properties != null) {
              properties.forEach((k, v) -> {
                if (k.equals("children")) {
                  System.out.println(123);
                }
                rootObjectNode.set(k, this.convertSchema2JsonNode(v));
              });
            }
            result = rootObjectNode;
          } else {
            result = this.fakePrimitiveType(type);
          }
        }
      }
    }
    return result;
  }

  @Override
  public JsonNode fakeObject(Schema schema) {
    return this.convertSchema2JsonNode(schema);
  }

  @SneakyThrows
  @Override
  public Schema getSchemaByRef(String ref) {
    if (MapUtils.isEmpty(this.schemas)) {
      throw new SchemasNullException("schemas is empty or null");
    }
    ref = ref.substring(ref.lastIndexOf("/") + 1);
    return this.schemas.get(ref);
  }


}
