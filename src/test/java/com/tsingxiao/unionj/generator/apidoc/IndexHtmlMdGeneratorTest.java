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
public class IndexHtmlMdGeneratorTest {

  @Test
  public void generate() {
    String api = MockDocParserTest.class.getClassLoader().getResource("petstore3.json").getPath();
    IndexHtmlMdGenerator indexHtmlMdGenerator = new IndexHtmlMdGenerator(api);
    String outputFile = indexHtmlMdGenerator.generate();
    File file = new File(outputFile);
    Assert.assertTrue(file.exists());
  }
}
