package com.tsingxiao.unionj.generator.openapi3.model;

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
  @JsonProperty("get")
  public Get get;

  @JsonProperty("post")
  public Post post;

  @JsonProperty("put")
  public Put put;

  @JsonProperty("delete")
  public Delete delete;
}
