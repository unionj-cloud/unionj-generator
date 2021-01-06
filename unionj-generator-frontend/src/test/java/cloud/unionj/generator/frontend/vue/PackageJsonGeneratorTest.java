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
public class PackageJsonGeneratorTest {

  @Test
  public void generate() {
    PackageJsonGenerator packageJsonGenerator = new PackageJsonGenerator("测试项目");
    String outputFile = packageJsonGenerator.generate();
    File file = new File(outputFile);
    Assert.assertTrue(file.exists());
  }
}
