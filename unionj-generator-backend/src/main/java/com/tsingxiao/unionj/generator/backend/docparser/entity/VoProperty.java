package com.tsingxiao.unionj.generator.backend.docparser.entity;

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

  public VoProperty(String name, String jsonProperty, String type) {
    this.name = name;
    this.jsonProperty = jsonProperty;
    this.type = type;
  }
}
