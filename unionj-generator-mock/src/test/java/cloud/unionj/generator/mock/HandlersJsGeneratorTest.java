package cloud.unionj.generator.mock;

import cloud.unionj.generator.mock.docparser.MockDocParser;
import cloud.unionj.generator.mock.docparser.entity.Api;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator
 * @date:2020/11/22
 */
public class HandlersJsGeneratorTest {

  @Test
  public void generate() throws IOException {
    try (BufferedInputStream is = new BufferedInputStream(ClassLoader.getSystemResourceAsStream("petstore3.json"))) {
      Api api = MockDocParser.parse(is);
      HandlersJsGenerator handlersGenerator = new HandlersJsGenerator(api);
      String outputFile = handlersGenerator.generate();
      File file = new File(outputFile);
      Assert.assertTrue(file.exists());
    }
  }
}
