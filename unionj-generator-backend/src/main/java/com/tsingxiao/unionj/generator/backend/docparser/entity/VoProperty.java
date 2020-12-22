package com.tsingxiao.unionj.generator.backend.docparser.entity;

import com.tsingxiao.unionj.generator.openapi3.model.Schema;
import lombok.Data;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.backend.docparser.entity
 * @date:2020/12/21
 */
@Data
public class VoProperty {

  private String name;
  private String jsonProperty;
  private String type;
  protected int level;

  public VoProperty(String name, String jsonProperty, String type) {
    this.name = name;
    this.jsonProperty = jsonProperty;
    this.type = type;
  }

  public VoProperty(String name, String jsonProperty, Schema type) {
    this.name = name;
    this.jsonProperty = jsonProperty;
    this.type = type.javaType();
  }

}
