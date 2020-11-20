package com.tsingxiao.unionj.docparser;

import com.fasterxml.jackson.databind.JsonNode;
import com.tsingxiao.unionj.docparser.entity.Api;
import com.tsingxiao.unionj.docparser.entity.ApiItem;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.docparser
 * @date:2020/11/18
 */
public class DocParserTest {

  @Test
  public void parse() {
    String testFilePath = DocParserTest.class.getClassLoader().getResource("test-openapi.json").getPath();
    DocParser docParser = new DocParser(testFilePath);
    Api api = docParser.parse();
    Assert.assertNotNull(api);
    ApiItem apiItem = api.getItems().stream().filter(item -> item.getEndpoint().equals("/hall/latestOrder")).findAny().get();
    Assert.assertFalse(apiItem.getResponse().isEmpty());
    JsonNode response = apiItem.getResponse();
    System.out.println(response);
  }
}
