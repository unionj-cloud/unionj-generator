package com.tsingxiao.unionj.generator.service.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.docparser.entity
 * @date:2020/11/18
 */
@Data
public class Router {

  private String name;
  private String endpoint;
  private String httpMethod;

  private ReqBody reqBody;
  private List<Property> pathParams;
  private List<Property> queryParams;


  // TODO need implementation
  private List<Property> headerParams;

}
