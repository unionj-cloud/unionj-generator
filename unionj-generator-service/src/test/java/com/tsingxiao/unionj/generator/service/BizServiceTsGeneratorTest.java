package com.tsingxiao.unionj.generator.service;

import com.tsingxiao.unionj.generator.service.docparser.ServiceDocParser;
import com.tsingxiao.unionj.generator.service.docparser.ServiceDocParserTest;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizServer;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.service
 * @date:2020/11/26
 */
public class BizServiceTsGeneratorTest {

  @Test
  public void generate() {
    String testFilePath = ClassLoader.getSystemResource("petstore3.json").getPath();
    ServiceDocParser docParser = new ServiceDocParser(testFilePath);
    BizServer bizServer = docParser.parse();
    BizServiceTsGenerator bizServiceTsGenerator = new BizServiceTsGenerator(bizServer.getName());
    String outputFile = bizServiceTsGenerator.generate();
    File file = new File(outputFile);
    Assert.assertTrue(file.exists());
  }
}
