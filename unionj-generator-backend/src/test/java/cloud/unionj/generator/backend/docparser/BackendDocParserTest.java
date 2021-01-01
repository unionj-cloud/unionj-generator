package cloud.unionj.generator.backend.docparser;

import cloud.unionj.generator.backend.docparser.entity.Backend;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.backend.docparser
 * @date 2020/12/22
 */
public class BackendDocParserTest {

  @Test
  public void parse() throws IOException {
    try (BufferedInputStream is = new BufferedInputStream(ClassLoader.getSystemResourceAsStream("petstore3.json"))) {
      Backend backend = BackendDocParser.parse(is);
      Assert.assertNotNull(backend);
    }
  }
}
