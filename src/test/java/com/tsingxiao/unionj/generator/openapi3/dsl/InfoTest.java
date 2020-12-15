package com.tsingxiao.unionj.generator.openapi3.dsl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static com.tsingxiao.unionj.generator.openapi3.dsl.Info.*;
import static com.tsingxiao.unionj.generator.openapi3.dsl.contact.contact;
import static com.tsingxiao.unionj.generator.openapi3.dsl.contact.email;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class InfoTest {

  @Test
  public void TestInfo1() throws JsonProcessingException {
    com.tsingxiao.unionj.generator.openapi3.model.Info info = info(() -> {
      title("测试Info dsl");
      description("test test");
      contact(() -> {
        email("328454505@qq.com");
      });
    });
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    System.out.println(objectMapper.writeValueAsString(info));
  }
}
