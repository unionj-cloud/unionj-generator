package cloud.unionj.generator.service;

import cloud.unionj.generator.service.docparser.ServiceDocParser;
import cloud.unionj.generator.service.docparser.entity.BizServer;
import cloud.unionj.generator.service.docparser.entity.BizService;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.service
 * date 2020/11/27
 */
public class ServiceTsGeneratorTest {

  @Test
  public void generate() throws IOException {
    try (BufferedInputStream is = new BufferedInputStream(ClassLoader.getSystemResourceAsStream("userservice_openapi3.json"))) {
      BizServer bizServer = ServiceDocParser.parse(is);
      for (BizService bizService : bizServer.getServices()) {
        ServiceTsGenerator serviceTsGenerator = new ServiceTsGenerator(bizService);
        String outputFile = serviceTsGenerator.generate();
        File file = new File(outputFile);
        Assert.assertTrue(file.exists());
      }
    }
  }
}
