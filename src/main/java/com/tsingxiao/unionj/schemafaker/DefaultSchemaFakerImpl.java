package com.tsingxiao.unionj.schemafaker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;
import com.github.javafaker.Faker;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.parser.util.SchemaTypeUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.schemafaker
 * @date:2020/11/19
 */
@Data
public class DefaultSchemaFakerImpl extends Faker implements SchemaFaker {

  private Faker faker = new Faker(Locale.SIMPLIFIED_CHINESE);
  private ObjectMapper objectMapper = new ObjectMapper();


  public DefaultSchemaFakerImpl(Faker faker) {
    this.faker = faker;
  }

  public DefaultSchemaFakerImpl() {
  }

  // TODO
  @Override
  public JsonNode fakeFormat(String format) {
    return null;
  }

  @Override
  public JsonNode fakePrimitiveType(String type) {
    JsonNode jsonNode = null;
    switch (type) {
      case SchemaTypeUtil.BOOLEAN_TYPE: {
        jsonNode = BooleanNode.valueOf(this.faker.bool().bool());
        break;
      }
      case SchemaTypeUtil.INTEGER_TYPE: {
        jsonNode = IntNode.valueOf(this.faker.random().nextInt(0, 10000));
        break;
      }
      case SchemaTypeUtil.NUMBER_TYPE: {
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

  @Override
  public ObjectNode fakeObject(Schema schema) {
    ObjectNode rootObjectNode = this.createRootObjectNode();
    Map<String, Schema> properties = schema.getProperties();
    if (properties == null) {
      return rootObjectNode;
    }
    properties.forEach((key, value) -> {
      String format = value.getFormat();
      if (StringUtils.isNotBlank(format)) {
        rootObjectNode.set(key, this.fakeFormat(format));
      } else {
        String type = value.getType();
        if (StringUtils.isNotBlank(type)) {
          if (!type.equals(SchemaTypeUtil.OBJECT_TYPE)) {
            rootObjectNode.set(key, this.fakePrimitiveType(type));
          } else {
            rootObjectNode.set(key, this.fakeObject(value));
          }
        }
      }
    });
    return rootObjectNode;
  }

  @Override
  public ObjectNode createRootObjectNode() {
    return this.objectMapper.createObjectNode();
  }

}
