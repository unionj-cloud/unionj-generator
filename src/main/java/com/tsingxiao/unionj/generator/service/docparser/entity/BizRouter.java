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
public class BizRouter {

  private String name;
  private String endpoint;
  private String httpMethod;

  private BizType reqBody;
  private List<BizProperty> pathParams;
  private List<BizProperty> queryParams;


  // TODO need implementation
  private List<BizProperty> headerParams;

}
