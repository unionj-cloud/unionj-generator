package com.tsingxiao.unionj.generator.mock;

import com.tsingxiao.unionj.docparser.DocParser;
import com.tsingxiao.unionj.docparser.DocParserTest;
import com.tsingxiao.unionj.docparser.entity.Api;
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
    String testFilePath = DocParserTest.class.getClassLoader().getResource("test-openapi.json").getPath();
    DocParser docParser = new DocParser(testFilePath);
    Api api = docParser.parse();
    MockFolderGenerator mockFolderGenerator = new MockFolderGenerator.Builder(api).zip(true).build();
    String outputFile = mockFolderGenerator.generate();
    File file = new File(outputFile);
    Assert.assertTrue(file.exists());
  }
}
