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
public class Content {

  @JsonProperty("application/json")
  private Schema applicationJson;


  @JsonProperty("application/x-www-form-urlencoded")
  private Schema applicationXWwwFormUrlencoded;

}
