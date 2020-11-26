package com.tsingxiao.unionj.generator.service.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.docparser.entity
 * @date:2020/11/18
 */

/**
 * ReqBody typescript interface representation
 */
@Data
public class ReqBody {

  private String name;
  private List<Property> properties;

}
