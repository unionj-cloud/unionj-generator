package com.tsingxiao.unionj.generator.openapi3.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.model
 * @date:2020/12/14
 */
@Data
public class Root {

  private String openapi = "3.0.2";
  private Info info;
  private ExternalDocs externalDocs;
  private List<Server> servers;
  private List<Tag> tags;
  private Map<String, Path> paths;
  private Components components;

}
