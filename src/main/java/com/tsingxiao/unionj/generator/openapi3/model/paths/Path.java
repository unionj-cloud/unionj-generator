package com.tsingxiao.unionj.generator.openapi3.model.paths;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.model
 * @date:2020/12/14
 */
@Data
public class Path {

  @JsonIgnore
  private String endpoint;

  @JsonProperty("get")
  private Get get;

  @JsonProperty("post")
  private Post post;

  @JsonProperty("put")
  private Put put;

  @JsonProperty("delete")
  private Delete delete;
}
