package com.tsingxiao.unionj.generator.openapi3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.tsingxiao.unionj.generator.openapi3.dsl.SchemaHelper;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.model
 * @date:2020/12/14
 */
@Data
public class Schema {

  @JsonProperty("$ref")
  private String ref;

  @JsonProperty("tree")
  private boolean tree;

  private String title;

  private String type;
  private String javaType;
  private Map<String, Schema> properties = new HashMap<>();
  private String format;
  private Schema items;
  private String description;

  // TODO
  private boolean uniqueItems;

  @JsonProperty("default")
  private Object defaultValue;

  private Object example;
  private boolean deprecated;
  private boolean nullable;
  private Discriminator discriminator;
  private Object maximum;
  private Object minimum;
  private Object exclusiveMaximum;
  private Object exclusiveMinimum;
  private Integer maxLength;
  private Integer minLength;
  private List<String> required = new ArrayList<>();

  @JsonProperty("enum")
  private List<String> enumValue = new ArrayList<>();

  private List<Schema> allOf = new ArrayList<>();
  private List<Schema> oneOf = new ArrayList<>();

  // TODO
  private List<Schema> anyOf;

  // TODO
  private List<Schema> not;

  // TODO
  private Schema additionalProperties;

  // TODO
  private Object pattern;

  public void properties(String property, Schema schema) {
    this.properties.put(property, schema);
  }

  public void required(String required) {
    this.required.add(required);
  }

  public void enumValue(String enumValue) {
    this.enumValue.add(enumValue);
  }

  public void allOf(Schema schema) {
    this.allOf.add(schema);
  }

  public void oneOf(Schema schema) {
    this.oneOf.add(schema);
  }

  public String javaType() {
    if (this instanceof Generic) {
      return this.title;
    }
    return this.deepSetType();
  }

  private String getTypeByRef(String ref) {
    String key = ref.substring(ref.lastIndexOf("/") + 1);
    if (StringUtils.isBlank(key)) {
      return Object.class.getSimpleName();
    }
    return key;
  }

  private String deepSetType() {
    if (StringUtils.isBlank(type)) {
      return this.getTypeByRef(ref);
    }
    String tsType;
    switch (type) {
      case "object": {
        if (additionalProperties != null) {
          String valueType = additionalProperties.deepSetType();
          tsType = "Map" + SchemaHelper.LEFT_ARROW + String.class.getSimpleName() + ", " + valueType + SchemaHelper.RIGHT_ARROW;
        } else if (format.equals("T")) {
          tsType = "T";
        } else {
          tsType = Object.class.getSimpleName();
        }
        break;
      }
      case "boolean": {
        tsType = Boolean.class.getSimpleName();
        break;
      }
      case "integer": {
        if (format.equals("int32")) {
          tsType = Integer.class.getSimpleName();
        } else {
          tsType = Long.class.getSimpleName();
        }
        break;
      }
      case "number": {
        if (format.equals("float")) {
          tsType = Float.class.getSimpleName();
        } else {
          tsType = Double.class.getSimpleName();
        }
        break;
      }
      case "string": {
        if (format != null && format.equals("date-time")) {
          tsType = java.util.Date.class.getSimpleName();
        } else {
          tsType = String.class.getSimpleName();
        }
        break;
      }
      case "array": {
        if (!uniqueItems) {
          String elementType;
          if (StringUtils.isNotBlank(items.getRef())) {
            elementType = this.getTypeByRef(items.getRef());
          } else {
            elementType = items.deepSetType();
          }
          tsType = "List" + SchemaHelper.LEFT_ARROW + elementType + SchemaHelper.RIGHT_ARROW;
        } else {
          String elementType;
          if (StringUtils.isNotBlank(items.getRef())) {
            elementType = this.getTypeByRef(items.getRef());
          } else {
            elementType = items.deepSetType();
          }
          tsType = "Set" + SchemaHelper.LEFT_ARROW + elementType + SchemaHelper.RIGHT_ARROW;
        }
        break;
      }
      default: {
        tsType = Object.class.getSimpleName();
      }
    }
    return tsType;
  }

  @SneakyThrows
  public Generic generic(Schema schema) {
    Gson gson = new Gson();
    Generic deepCopy = gson.fromJson(gson.toJson(this), Generic.class);
    List<String> genericPropertyList = new ArrayList<>();
    deepCopy.getProperties().forEach((k, v) -> {
      if (v.equals(SchemaHelper.T)) {
        genericPropertyList.add(k);
      }
    });
    genericPropertyList.stream().forEach(property -> {
      deepCopy.properties(property, schema);
    });
    if (StringUtils.isNotBlank(schema.getTitle())) {
      deepCopy.setTitle(deepCopy.getTitle() + SchemaHelper.LEFT_ARROW + schema.getTitle() + SchemaHelper.RIGHT_ARROW);
    } else {
      String type = schema.javaType();
      deepCopy.setTitle(deepCopy.getTitle() + SchemaHelper.LEFT_ARROW + type + SchemaHelper.RIGHT_ARROW);
    }
    return deepCopy;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .toString();
  }
}
