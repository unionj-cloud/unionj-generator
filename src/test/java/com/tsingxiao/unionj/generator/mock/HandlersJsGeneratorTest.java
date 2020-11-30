package com.tsingxiao.unionj.generator.mock;

import com.tsingxiao.unionj.generator.mock.docparser.MockDocParser;
import com.tsingxiao.unionj.generator.mock.docparser.MockDocParserTest;
import com.tsingxiao.unionj.generator.mock.docparser.entity.Api;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator
 * @date:2020/11/22
 */
public class HandlersJsGeneratorTest {

  @Test
  public void generate() {
    String testFilePath = MockDocParserTest.class.getClassLoader().getResource("petstore3.json").getPath();
    MockDocParser docParser = new MockDocParser(testFilePath);
    Api api = docParser.parse();
    HandlersJsGenerator handlersGenerator = new HandlersJsGenerator(api);
    String outputFile = handlersGenerator.generate();
    File file = new File(outputFile);
    Assert.assertTrue(file.exists());
  }
}
