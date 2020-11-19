package com.tsingxiao.unionj.docparser.entity;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.docparser.entity
 * @date:2020/11/18
 */
@Data
public class ApiItem {

  private String endpoint;
  private String method;
  private List<String> bodyParams;
  private List<String> pathParams;
  private List<String> queryParams;
  private JsonNode response;

}
