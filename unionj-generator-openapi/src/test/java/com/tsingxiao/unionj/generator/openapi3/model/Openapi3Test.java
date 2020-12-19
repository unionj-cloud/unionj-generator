package com.tsingxiao.unionj.generator.openapi3.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.model
 * @date:2020/12/19
 */
public class Openapi3Test {

  @Test
  public void parse() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    try (InputStream is = ClassLoader.getSystemResourceAsStream("petstore3.json")) {
      JsonNode jsonNode = objectMapper.readTree(is);
      System.out.println(jsonNode);
    }
  }
}
