package cloud.unionj.generator.frontend.vue;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.frontend.vue
 *  date 2020/11/26
 */
public class VueProjectGeneratorTest {

  @Test
  public void generate() {
    String testFilePath = ClassLoader.getSystemResource("test.json").getPath();
    VueProjectGenerator vueProjectGenerator = new VueProjectGenerator.Builder("my-awesome-project").doc(testFilePath).scaffold(true).build();
    String outputFile = vueProjectGenerator.generate();
    File file = new File(outputFile);
    Assert.assertTrue(file.exists());
  }
}
