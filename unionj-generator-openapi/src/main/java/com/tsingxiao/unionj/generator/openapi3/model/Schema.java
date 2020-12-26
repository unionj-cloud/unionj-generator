package com.tsingxiao.unionj.generator.openapi3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
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

  @JsonIgnore
  private String title;

  private String type;
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

  @JsonIgnore
  private int level;

  public String javaType() {
    String tsType = this.deepSetType();
    for (int i = 0; i < this.level; i++) {
      tsType += "[]";
    }
    this.level = 0;
    return tsType;
  }

  private String getTypeByRef(String ref) {
    String key = ref.substring(ref.lastIndexOf("/") + 1);
    if (StringUtils.isBlank(key)) {
      return Object.class.getSimpleName();
    }
    return key.replaceAll("[^a-zA-Z]", "");
  }

  private String deepSetType() {
    if (StringUtils.isBlank(type)) {
      return this.getTypeByRef(ref);
    }
    String tsType;
    switch (type) {
      case "boolean": {
        tsType = "boolean";
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
        tsType = String.class.getSimpleName();
        break;
      }
      case "array": {
        this.level++;
        if (StringUtils.isNotBlank(items.getRef())) {
          tsType = this.getTypeByRef(items.getRef());
        } else {
          tsType = items.deepSetType();
        }
        break;
      }
      default: {
        tsType = Object.class.getSimpleName();
      }
    }
    return tsType;
  }


  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .toString();
  }
}
