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

  public void isTree(boolean isTree) {
    this.schema.setTree(isTree);
  }

  public void title(String title) {
    this.schema.setTitle(title);
  }

  public void type(String type) {
    this.schema.setType(type);
  }

  public void javaType(String canonical) {
    this.schema.setJavaType(canonical);
  }

  public void javaType(Class<?> javaType) {
    this.schema.setJavaType(javaType.getCanonicalName());
  }

  public void properties(String property, Schema schema) {
    this.schema.properties(property, schema);
  }

  public void format(String format) {
    this.schema.setFormat(format);
  }

  public void items(Schema items) {
    this.schema.setItems(items);
  }

  public void additionalProperties(Schema additionalProperties) {
    this.schema.setAdditionalProperties(additionalProperties);
  }

  public void uniqueItems(boolean uniqueItems) {
    this.schema.setUniqueItems(uniqueItems);
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
    this.schema.required(required);
  }

  public void enumValue(String enumValue) {
    this.schema.enumValue(enumValue);
  }

  public void allOf(Schema schema) {
    this.schema.allOf(schema);
  }

  public void oneOf(Schema schema) {
    this.schema.oneOf(schema);
  }

  public void discriminator(Discriminator discriminator) {
    this.schema.setDiscriminator(discriminator);
  }

  public Schema build() {
    return this.schema;
  }
}
