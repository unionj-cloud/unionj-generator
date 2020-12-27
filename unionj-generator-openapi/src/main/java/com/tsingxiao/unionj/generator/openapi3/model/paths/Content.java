package com.tsingxiao.unionj.generator.openapi3.model.paths;

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

  @JsonProperty("text/plain")
  private MediaType textPlain;

  @JsonProperty("application/json")
  private MediaType applicationJson;

  /**
   * requestBody:
   *   content:
   *     application/x-www-form-urlencoded:
   *       schema:
   *         type: object
   *         properties:
   *           id:
   *             type: string
   *             format: uuid
   *           address:
   *             # complex types are stringified to support RFC 1866
   *             type: object
   *             properties: {}
   */
  @JsonProperty("application/x-www-form-urlencoded")
  private MediaType applicationXWwwFormUrlencoded;

  /**
   * for single file upload
   *
   * requestBody:
   *   content:
   *     application/octet-stream:
   *       schema:
   *         # a binary file of any type
   *         type: string
   *         format: binary
   */
  @JsonProperty("application/octet-stream")
  private MediaType applicationOctetStream;

  /**
   * for multiple files upload
   *
   * requestBody:
   *   content:
   *     multipart/form-data:
   *       schema:
   *         properties:
   *           # The property name 'file' will be used for all files.
   *           file:
   *             type: array
   *             items:
   *               type: string
   *               format: binary
   */
  @JsonProperty("multipart/form-data")
  private MediaType multipartFormData;

}
