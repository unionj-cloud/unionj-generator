package com.tsingxiao.unionj.generator.service;

import com.tsingxiao.unionj.generator.service.docparser.ServiceDocParser;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizServer;
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
    String testFilePath = ServiceFolderGeneratorTest.class.getClassLoader().getResource("petstore3.json").getPath();
    ServiceDocParser docParser = new ServiceDocParser(testFilePath);
    BizServer bizServer = docParser.parse();
    ServiceFolderGenerator serviceFolderGenerator = new ServiceFolderGenerator.Builder(bizServer).zip(false).build();
    String outputFile = serviceFolderGenerator.generate();
    File file = new File(outputFile);
    Assert.assertTrue(file.exists());
  }
}
