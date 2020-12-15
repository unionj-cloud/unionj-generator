package com.tsingxiao.unionj.generator.openapi3.model;

import lombok.Data;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.model
 * @date:2020/12/14
 */
@Data
public class Parameter {

  private String name;
  private String in;
  private String description;
  private boolean required;
  private boolean deprecated;
  private Object example;
  public Schema schema;

  // TODO
  private String style;

  // TODO
  private boolean explode;

  // TODO
  private boolean allowReserved;

  // TODO
  private Content content;


}
