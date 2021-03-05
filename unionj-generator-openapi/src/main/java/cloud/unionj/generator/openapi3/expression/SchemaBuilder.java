package cloud.unionj.generator.openapi3.expression;

import cloud.unionj.generator.openapi3.model.Discriminator;
import cloud.unionj.generator.openapi3.model.Schema;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.expression
 * date 2020/12/16
 */
public class SchemaBuilder {

  protected Schema schema;

  public SchemaBuilder(ISchemaFinder schemaFinder) {
    this.schema = new Schema(schemaFinder);
  }

  public void isTree(boolean isTree) {
    this.schema.setTree(isTree);
  }

  public void dummy(String dummy) {
    this.schema.setDummy(dummy);
  }

  public SchemaBuilder title(String title) {
    this.schema.setTitle(title);
    return this;
  }

  public SchemaBuilder type(String type) {
    this.schema.setType(type);
    return this;
  }

  public SchemaBuilder javaType(String canonical) {
    this.schema.setJavaType(canonical);
    return this;
  }

  public SchemaBuilder javaType(Class<?> javaType) {
    this.schema.setJavaType(javaType.getCanonicalName());
    return this;
  }

  public SchemaBuilder properties(String property, Schema schema) {
    this.schema.properties(property, schema);
    return this;
  }

  public SchemaBuilder format(String format) {
    this.schema.setFormat(format);
    return this;
  }

  public SchemaBuilder items(Schema items) {
    this.schema.setItems(items);
    return this;
  }

  public SchemaBuilder additionalProperties(Schema additionalProperties) {
    this.schema.setAdditionalProperties(additionalProperties);
    return this;
  }

  public SchemaBuilder uniqueItems(boolean uniqueItems) {
    this.schema.setUniqueItems(uniqueItems);
    return this;
  }

  public SchemaBuilder description(String description) {
    this.schema.setDescription(description);
    return this;
  }

  public SchemaBuilder defaultValue(Object defaultValue) {
    this.schema.setDefaultValue(defaultValue);
    return this;
  }

  public SchemaBuilder example(Object example) {
    this.schema.setExample(example);
    return this;
  }

  public SchemaBuilder deprecated(boolean deprecated) {
    this.schema.setDeprecated(deprecated);
    return this;
  }

  public SchemaBuilder nullable(boolean nullable) {
    this.schema.setNullable(nullable);
    return this;
  }

  public SchemaBuilder maximum(Object maximum) {
    this.schema.setMaximum(maximum);
    return this;
  }

  public SchemaBuilder minimum(Object minimum) {
    this.schema.setMinimum(minimum);
    return this;
  }

  public SchemaBuilder exclusiveMaximum(Object exclusiveMaximum) {
    this.schema.setExclusiveMaximum(exclusiveMaximum);
    return this;
  }

  public SchemaBuilder exclusiveMinimum(Object exclusiveMinimum) {
    this.schema.setExclusiveMinimum(exclusiveMinimum);
    return this;
  }

  public SchemaBuilder maxLength(Integer maxLength) {
    this.schema.setMaxLength(maxLength);
    return this;
  }

  public SchemaBuilder minLength(Integer minLength) {
    this.schema.setMinLength(minLength);
    return this;
  }

  public SchemaBuilder required(String required) {
    this.schema.required(required);
    return this;
  }

  public SchemaBuilder enumValue(String enumValue) {
    this.schema.enumValue(enumValue);
    return this;
  }

  public SchemaBuilder allOf(Schema schema) {
    this.schema.allOf(schema);
    return this;
  }

  public SchemaBuilder oneOf(Schema schema) {
    this.schema.oneOf(schema);
    return this;
  }

  public SchemaBuilder discriminator(Discriminator discriminator) {
    this.schema.setDiscriminator(discriminator);
    return this;
  }

  public Schema build() {
    return this.schema;
  }
}
