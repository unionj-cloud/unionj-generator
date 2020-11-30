package com.tsingxiao.unionj.generator.apidoc;

import com.tsingxiao.unionj.generator.mock.docparser.MockDocParserTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.apidoc
 * @date:2020/11/26
 */
public class ApiDocFolderGeneratorTest {

  @Test
  public void generate() {
    String doc = MockDocParserTest.class.getClassLoader().getResource("petstore3.json").getPath();
    ApiDocFolderGenerator apiDocFolderGenerator = new ApiDocFolderGenerator.Builder(doc).zip(false).build();
    String outputFile = apiDocFolderGenerator.generate();
    File file = new File(outputFile);
    Assert.assertTrue(file.exists());
  }
}
