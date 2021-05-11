package cloud.unionj.generator.openapi3.expression.paths;

import cloud.unionj.generator.openapi3.model.Schema;
import cloud.unionj.generator.openapi3.model.paths.Parameter;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.expression
 * date 2020/12/14
 */
public class ParameterBuilder {

  private Parameter parameter;

  private ParameterBuilder() {
    this.parameter = new Parameter();
  }

  public static ParameterBuilder builder() {
    return new ParameterBuilder();
  }

  public ParameterBuilder name(String name) {
    this.parameter.setName(name);
    return this;
  }

  public ParameterBuilder in(String in) {
    this.parameter.setIn(Parameter.InEnum.fromValue(in));
    return this;
  }

  public ParameterBuilder in(Parameter.InEnum in) {
    this.parameter.setIn(in);
    return this;
  }

  public ParameterBuilder description(String description) {
    this.parameter.setDescription(description);
    return this;
  }

  public ParameterBuilder required(boolean required) {
    this.parameter.setRequired(required);
    return this;
  }

  public ParameterBuilder deprecated(boolean deprecated) {
    this.parameter.setDeprecated(deprecated);
    return this;
  }

  public ParameterBuilder example(Object example) {
    this.parameter.setExample(example);
    return this;
  }

  public ParameterBuilder schema(Schema schema) {
    this.parameter.setSchema(schema);
    return this;
  }

  public Parameter build() {
    return this.parameter;
  }
}
