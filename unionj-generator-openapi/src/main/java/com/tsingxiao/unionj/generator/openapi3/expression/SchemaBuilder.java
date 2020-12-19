package com.tsingxiao.unionj.generator.openapi3.expression;

import com.tsingxiao.unionj.generator.openapi3.model.Discriminator;
import com.tsingxiao.unionj.generator.openapi3.model.Schema;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.expression
 * @date:2020/12/16
 */
public class SchemaBuilder {

  private Schema schema;

  public SchemaBuilder() {
    this.schema = new Schema();
  }

  public void type(String type) {
    this.schema.setType(type);
  }

  public void properties(String property, Schema schema) {
    this.schema.setProperties(property, schema);
  }

  public void format(String format) {
    this.schema.setFormat(format);
  }

  public void items(Schema items) {
    this.schema.setItems(items);
  }

  public void description(String description) {
    this.schema.setDescription(description);
  }

  public void defaultValue(Object defaultValue) {
    this.schema.setDefaultValue(defaultValue);
  }

  public void example(Object example) {
    this.schema.setExample(example);
  }

  public void deprecated(boolean deprecated) {
    this.schema.setDeprecated(deprecated);
  }

  public void nullable(boolean nullable) {
    this.schema.setNullable(nullable);
  }

  public void maximum(Object maximum) {
    this.schema.setMaximum(maximum);
  }

  public void minimum(Object minimum) {
    this.schema.setMinimum(minimum);
  }

  public void exclusiveMaximum(Object exclusiveMaximum) {
    this.schema.setExclusiveMaximum(exclusiveMaximum);
  }

  public void exclusiveMinimum(Object exclusiveMinimum) {
    this.schema.setExclusiveMinimum(exclusiveMinimum);
  }

  public void maxLength(Integer maxLength) {
    this.schema.setMaxLength(maxLength);
  }

  public void minLength(Integer minLength) {
    this.schema.setMinLength(minLength);
  }

  public void required(String required) {
    this.schema.setRequired(required);
  }

  public void enumValue(String enumValue) {
    this.schema.setEnumValue(enumValue);
  }

  public void allOf(Schema schema) {
    this.schema.setAllOf(schema);
  }

  public void oneOf(Schema schema) {
    this.schema.setOneOf(schema);
  }

  public void discriminator(Discriminator discriminator) {
    this.schema.setDiscriminator(discriminator);
  }

  public Schema build() {
    return this.schema;
  }
}
