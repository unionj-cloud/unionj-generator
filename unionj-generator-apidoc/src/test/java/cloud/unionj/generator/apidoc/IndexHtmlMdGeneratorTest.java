package cloud.unionj.generator.apidoc;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.apidoc
 * @date:2020/11/26
 */
public class IndexHtmlMdGeneratorTest {

  @Test
  public void generate() {
    String api = ClassLoader.getSystemResource("petstore3.json").getPath();
    IndexHtmlMdGenerator indexHtmlMdGenerator = new IndexHtmlMdGenerator(api);
    String outputFile = indexHtmlMdGenerator.generate();
    File file = new File(outputFile);
    Assert.assertTrue(file.exists());
  }
}
