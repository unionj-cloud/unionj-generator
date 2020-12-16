package com.tsingxiao.unionj.generator.openapi3.expression;

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

  /**
   * private Schema items;
   * private String description;
   *
   * @JsonProperty("default") private Object defaultValue;
   * <p>
   * private Object example;
   * private boolean deprecated;
   * private boolean nullable;
   * private Discriminator discriminator;
   * private Object maximum;
   * private Object minimum;
   * private Object exclusiveMaximum;
   * private Object exclusiveMinimum;
   * private Integer maxLength;
   * private Integer minLength;
   * private List<String> required;
   * @JsonProperty("enum") private List<String> enumValue;
   * <p>
   * private List<Schema> allOf;
   * private List<Schema> oneOf;
   */

  public void type(String type) {
    this.schema.setType(type);
  }

  public void properties(String property, Schema schema) {
    this.schema.setProperties(property, schema);
  }

  public void format(String format) {
    this.schema.setFormat(format);
  }

  public Schema build() {
    return this.schema;
  }

}
