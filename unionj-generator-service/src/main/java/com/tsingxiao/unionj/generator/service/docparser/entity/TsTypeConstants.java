package com.tsingxiao.unionj.generator.service.docparser.entity;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.service.docparser.entity
 * @date:2020/11/27
 */
public class TsTypeConstants {

  public static final String ANY = "any";
  public static final String FORMDATA = "FormData";
  public static final String STRING = "string";
  public static final String NUMBER = "number";
  public static final String BOOLEAN = "boolean";

  public static final List<String> values() {
    return Lists.newArrayList(ANY, NUMBER, STRING, BOOLEAN);
  }
}
