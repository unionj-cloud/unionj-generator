package com.tsingxiao.unionj.generator.mock.docparser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsingxiao.unionj.generator.mock.docparser.entity.Api;
import com.tsingxiao.unionj.generator.mock.docparser.entity.ApiItem;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.docparser
 * @date:2020/11/18
 */
public class MockDocParserTest {

  @Test
  public void parse() throws IOException {
    try (BufferedInputStream is = new BufferedInputStream(ClassLoader.getSystemResourceAsStream("petstore3.json"))) {
      Api api = MockDocParser.parse(is);
      Assert.assertNotNull(api);
    }
  }
}
