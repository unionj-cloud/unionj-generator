package com.tsingxiao.unionj.generator.service.docparser.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.docparser.entity
 * @date:2020/11/18
 */
@Data
public class BizServer {
  private String name;
  private List<BizService> services;
  private List<BizType> types;
}
