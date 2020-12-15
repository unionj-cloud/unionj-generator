package com.tsingxiao.unionj.generator.openapi3.dsl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsingxiao.unionj.generator.openapi3.model.Openapi3;
import org.junit.Test;

import static com.tsingxiao.unionj.generator.openapi3.dsl.info.Contact.contact;
import static com.tsingxiao.unionj.generator.openapi3.dsl.info.Contact.email;
import static com.tsingxiao.unionj.generator.openapi3.dsl.info.Info.*;
import static com.tsingxiao.unionj.generator.openapi3.dsl.info.License.*;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class InfoTest {

  @Test
  public void TestInfo1() throws JsonProcessingException {
    Openapi3 openapi3 = openapi3(() -> {
      info(() -> {
        title("测试Info dsl");
        description("test test");
        version("v0.0.1");
        contact(() -> {
          email("328454505@qq.com");
        });
        license(() -> {
          url("www.unionj.com");
          name("unionj");
        });
      });
    });
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    System.out.println(objectMapper.writeValueAsString(openapi3));
  }
}
