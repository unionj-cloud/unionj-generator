package com.tsingxiao.unionj.generator.mock;

import com.tsingxiao.unionj.generator.mock.docparser.MockDocParser;
import com.tsingxiao.unionj.generator.mock.docparser.entity.Api;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock
 * @date:2020/11/26
 */
public class MockFolderGeneratorTest {

  @Test
  public void generate() {
    String testFilePath = ClassLoader.getSystemResource("petstore3.json").getPath();
    MockDocParser docParser = new MockDocParser(testFilePath);
    Api api = docParser.parse();
    MockFolderGenerator mockFolderGenerator = new MockFolderGenerator.Builder(api).zip(false).build();
    String outputFile = mockFolderGenerator.generate();
    File file = new File(outputFile);
    Assert.assertTrue(file.exists());
  }
}
