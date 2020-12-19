package com.tsingxiao.unionj.generator.mock.docparser.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.NullNode;
import com.tsingxiao.unionj.generator.ApiItemVo;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.docparser.entity
 * @date:2020/11/18
 */
@Data
public class ApiItem {

  protected static ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

  protected String endpoint;
  protected String method;
  protected String tag = "未分类";
  protected List<String> bodyParams;
  protected List<String> pathParams;
  protected List<String> queryParams;

  // TODO need implementation
  protected List<String> headerParams;

  protected JsonNode response;


  @SneakyThrows
  public ApiItemVo toApiItemVo() {
    ApiItemVo apiItemVo = mapper.readValue(mapper.writeValueAsString(this), ApiItemVo.class);
    if (apiItemVo.getResponse() instanceof NullNode) {
      return apiItemVo;
    }
    apiItemVo.setResponseStr(mapper.writeValueAsString(apiItemVo.getResponse()));
    apiItemVo.setResponse(null);
    return apiItemVo;
  }

}
