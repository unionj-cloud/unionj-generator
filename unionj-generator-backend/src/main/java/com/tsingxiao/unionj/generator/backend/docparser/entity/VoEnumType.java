package com.tsingxiao.unionj.generator.backend.docparser.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.backend.docparser.entity
 * @date:2020/12/21
 */
@Data
public class VoEnumType {

  private List<VoEnum> enums;
  private String name;

  public VoEnumType(List<VoEnum> enums, String name) {
    this.enums = enums;
    this.name = name;
  }
}
