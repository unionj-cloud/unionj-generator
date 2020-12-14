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
public class Responses {

  @JsonProperty("200")
  private Response response200;

  @JsonProperty("400")
  private Response response400;

  @JsonProperty("404")
  private Response response404;

  @JsonProperty("405")
  private Response response405;
}
