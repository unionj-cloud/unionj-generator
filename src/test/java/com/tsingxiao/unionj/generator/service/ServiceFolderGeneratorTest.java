package com.tsingxiao.unionj.generator.service;

import com.tsingxiao.unionj.generator.service.docparser.ServiceDocParserTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.service
 * @date:2020/11/27
 */
public class ServiceFolderGeneratorTest {

  @Test
  public void generate() {
    String testFilePath = ServiceDocParserTest.class.getClassLoader().getResource("test-openapi.json").getPath();
    ServiceFolderGenerator serviceFolderGenerator = new ServiceFolderGenerator(testFilePath);
    String outputFile = serviceFolderGenerator.generate();
    File file = new File(outputFile);
    Assert.assertTrue(file.exists());
  }
}
