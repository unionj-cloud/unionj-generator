package com.tsingxiao.unionj.generator.apidoc;

import com.tsingxiao.unionj.generator.mock.docparser.DocParserTest;
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
    String api = DocParserTest.class.getClassLoader().getResource("test-openapi.json").getPath();
    ApiDocFolderGenerator apiDocFolderGenerator = new ApiDocFolderGenerator(api);
    String outputFile = apiDocFolderGenerator.generate();
    File file = new File(outputFile);
    Assert.assertTrue(file.exists());
  }
}
