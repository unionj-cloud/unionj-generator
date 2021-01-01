package cloud.unionj.generator.mock;

import cloud.unionj.generator.mock.docparser.MockDocParser;
import cloud.unionj.generator.mock.docparser.entity.Api;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.mock
 * @date 2020/11/26
 */
public class MockFolderGeneratorTest {

  @Test
  public void generate() throws IOException {
    try (BufferedInputStream is = new BufferedInputStream(ClassLoader.getSystemResourceAsStream("petstore3.json"))) {
      Api api = MockDocParser.parse(is);
      MockFolderGenerator mockFolderGenerator = new MockFolderGenerator.Builder(api).zip(false).build();
      String outputFile = mockFolderGenerator.generate();
      File file = new File(outputFile);
      Assert.assertTrue(file.exists());
    }
  }
}
