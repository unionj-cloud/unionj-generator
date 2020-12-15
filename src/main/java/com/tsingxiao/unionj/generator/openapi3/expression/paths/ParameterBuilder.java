package com.tsingxiao.unionj.generator.openapi3.expression.paths;

import com.tsingxiao.unionj.generator.openapi3.model.Parameter;
import com.tsingxiao.unionj.generator.openapi3.model.RequestBody;
import com.tsingxiao.unionj.generator.openapi3.model.Responses;
import com.tsingxiao.unionj.generator.openapi3.model.paths.Parameter;

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

  /**
   * private String name;
   *   private String in;
   *   private String description;
   *   private boolean required;
   *   private boolean deprecated;
   *   private Object example;
   *   public Schema schema;
   * @param summary
   */

  public void summary(String summary) {
    this.parameter.setExplode(summary);
  }

  public Parameter build() {
    return this.parameter;
  }
}
