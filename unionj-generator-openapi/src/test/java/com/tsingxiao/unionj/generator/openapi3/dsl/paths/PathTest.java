package com.tsingxiao.unionj.generator.openapi3.dsl.paths;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tsingxiao.unionj.generator.openapi3.model.Openapi3;
import org.junit.Test;

import static com.tsingxiao.unionj.generator.openapi3.dsl.Openapi3.openapi3;
import static com.tsingxiao.unionj.generator.openapi3.dsl.paths.Components.importComponents;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl.paths
 * @date:2020/12/16
 */
public class PathTest {

  @Test
  public void TestPath() throws JsonProcessingException {
    Openapi3 openapi3 = openapi3(ob -> {
      importComponents();
    });
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    System.out.println(objectMapper.writeValueAsString(openapi3));
  }
}
