package com.tsingxiao.unionj.generator.openapi3.model;

import lombok.Data;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.model
 * @date:2020/12/14
 */
@Data
public class Info {
  private String title;
  private String description;
  private String termsOfService;
  private Contact contact;
  private License license;
  private String version;
}
