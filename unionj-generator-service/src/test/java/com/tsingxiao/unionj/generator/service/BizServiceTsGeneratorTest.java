package com.tsingxiao.unionj.generator.service;

import com.tsingxiao.unionj.generator.service.docparser.ServiceDocParser;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizServer;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.service
 * @date:2020/11/26
 */
public class BizServiceTsGeneratorTest {

  @Test
  public void generate() throws IOException {
    try (BufferedInputStream is = new BufferedInputStream(ClassLoader.getSystemResourceAsStream("petstore3.json"))) {
      BizServer bizServer = ServiceDocParser.parse(is);
      BizServiceTsGenerator bizServiceTsGenerator = new BizServiceTsGenerator(bizServer.getName());
      String outputFile = bizServiceTsGenerator.generate();
      File file = new File(outputFile);
      Assert.assertTrue(file.exists());
    }
  }
}
