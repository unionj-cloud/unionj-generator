package com.tsingxiao.unionj.generator.openapi3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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

  private String type;
  private Map<String, Schema> properties = new HashMap<>();
  private String format;
  private Schema items;
  private String description;

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
}