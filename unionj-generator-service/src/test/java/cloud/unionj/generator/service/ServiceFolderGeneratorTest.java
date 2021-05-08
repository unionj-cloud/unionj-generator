package cloud.unionj.generator.service;

import cloud.unionj.generator.service.docparser.ServiceDocParser;
import cloud.unionj.generator.service.docparser.entity.BizServer;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.service
 *  date 2020/11/27
 */
public class ServiceFolderGeneratorTest {

  @Test
  public void generate() throws IOException {
    try (BufferedInputStream is = new BufferedInputStream(ClassLoader.getSystemResourceAsStream("test1.json"))) {
      BizServer bizServer = ServiceDocParser.parse(is);
      ServiceFolderGenerator serviceFolderGenerator = new ServiceFolderGenerator.Builder(bizServer).zip(false).build();
      String outputFile = serviceFolderGenerator.generate();
      File file = new File(outputFile);
      Assert.assertTrue(file.exists());
    }
  }
}
