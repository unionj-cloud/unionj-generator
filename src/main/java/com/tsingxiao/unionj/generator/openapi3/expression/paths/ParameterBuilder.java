package com.tsingxiao.unionj.generator.openapi3.expression.paths;

import com.tsingxiao.unionj.generator.openapi3.model.paths.Parameter;
import com.tsingxiao.unionj.generator.openapi3.model.Schema;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.expression
 * @date:2020/12/14
 */
public class ParameterBuilder {

  private Parameter parameter;

  public ParameterBuilder() {
    this.parameter = new Parameter();
  }

  public void name(String name) {
    this.parameter.setName(name);
  }

  public void in(String in) {
    this.parameter.setIn(in);
  }

  public void description(String description) {
    this.parameter.setDescription(description);
  }

  public void required(boolean required) {
    this.parameter.setRequired(required);
  }

  public void deprecated(boolean deprecated) {
    this.parameter.setDeprecated(deprecated);
  }

  public void example(Object example) {
    this.parameter.setExample(example);
  }

  public void schema(Schema schema) {
    this.parameter.setSchema(schema);
  }

  public Parameter build() {
    return this.parameter;
  }
}
