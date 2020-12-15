package com.tsingxiao.unionj.generator.openapi3.model;

import lombok.Data;

import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.model
 * @date:2020/12/14
 */
@Data
public class Components {

  private Map<String, Schema> schemas;

  // TODO
  private Map<String, Response> responses;

  // TODO
  private Map<String, Parameter> parameters;

  // TODO
  private Map<String, Example> examples;

  // TODO
  private Map<String, RequestBody> requestBodies;

  // TODO
  private Map<String, Header> headers;

  // TODO
  private Map<String, SecurityScheme> securitySchemes;

  // TODO
  private Map<String, Link> links;

  // TODO
  private Map<String, Callback> callbacks;

}