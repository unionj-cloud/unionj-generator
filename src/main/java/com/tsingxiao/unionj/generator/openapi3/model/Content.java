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

  @Data
  public static class ApplicationJson {
    private Schema schema;
  }

  @Data
  public class ApplicationXWwwFormUrlencoded {
    private Schema schema;
  }

  @JsonProperty("application/json")
  private ApplicationJson applicationJson;


  @JsonProperty("application/x-www-form-urlencoded")
  private ApplicationXWwwFormUrlencoded applicationXWwwFormUrlencoded;

}
