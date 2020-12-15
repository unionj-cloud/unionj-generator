package com.tsingxiao.unionj.generator.openapi3.model;

import lombok.Data;

import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.model
 * @date:2020/12/16
 */
@Data
public class Discriminator {

  private String propertyName;
  private Map<String, String> mapping;
}
