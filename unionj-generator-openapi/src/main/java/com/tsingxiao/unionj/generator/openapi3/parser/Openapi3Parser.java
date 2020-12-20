package com.tsingxiao.unionj.generator.openapi3.parser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsingxiao.unionj.generator.openapi3.model.Openapi3;

import java.io.*;
import java.net.URL;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.parser
 * @date:2020/12/20
 */
public class Openapi3Parser {

  public Openapi3 parse(File doc) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(doc))) {
      Openapi3 openapi3 = objectMapper.readValue(is, Openapi3.class);
      return openapi3;
    }
  }

  public Openapi3 parse(URL doc) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    try (BufferedInputStream is = new BufferedInputStream(doc.openStream())) {
      Openapi3 openapi3 = objectMapper.readValue(is, Openapi3.class);
      return openapi3;
    }
  }

  public Openapi3 parse(InputStream doc) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    Openapi3 openapi3 = objectMapper.readValue(doc, Openapi3.class);
    return openapi3;
  }
}
