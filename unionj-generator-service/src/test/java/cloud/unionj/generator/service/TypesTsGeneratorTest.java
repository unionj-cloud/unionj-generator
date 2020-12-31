package cloud.unionj.generator.service;

import cloud.unionj.generator.service.docparser.ServiceDocParser;
import cloud.unionj.generator.service.docparser.entity.BizServer;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.service
 * @date:2020/11/27
 */
public class TypesTsGeneratorTest {

  @Test
  public void generate() throws IOException {
    try (BufferedInputStream is = new BufferedInputStream(ClassLoader.getSystemResourceAsStream("petstore3.json"))) {
      BizServer bizServer = ServiceDocParser.parse(is);
      TypesTsGenerator typesTsGenerator = new TypesTsGenerator(bizServer.getTypes());
      String outputFile = typesTsGenerator.generate();
      File file = new File(outputFile);
      Assert.assertTrue(file.exists());
    }
  }
}
