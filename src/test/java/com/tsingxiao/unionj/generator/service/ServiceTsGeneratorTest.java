package com.tsingxiao.unionj.generator.service;

import com.tsingxiao.unionj.generator.service.docparser.ServiceDocParser;
import com.tsingxiao.unionj.generator.service.docparser.ServiceDocParserTest;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizServer;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizService;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.service
 * @date:2020/11/27
 */
public class ServiceTsGeneratorTest {

  @Test
  public void generate() {
    String testFilePath = ServiceDocParserTest.class.getClassLoader().getResource("test-openapi.json").getPath();
    ServiceDocParser docParser = new ServiceDocParser(testFilePath);
    BizServer bizServer = docParser.parse();
    for (BizService bizService : bizServer.getServices()) {
      ServiceTsGenerator serviceTsGenerator = new ServiceTsGenerator(bizService);
      String outputFile = serviceTsGenerator.generate();
      File file = new File(outputFile);
      Assert.assertTrue(file.exists());
    }
  }
}
