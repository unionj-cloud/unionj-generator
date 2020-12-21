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
public class Proto {

  private List<String> imports;
  private String base;
  private String name;
  private List<ProtoRouter> routers;

}
