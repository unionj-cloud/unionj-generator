package cloud.unionj.generator.mock.docparser;

import cloud.unionj.generator.mock.docparser.entity.Api;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.mock.docparser
 * @date:2020/11/18
 */
public class MockDocParserTest {

  @Test
  public void parse() throws IOException {
    try (BufferedInputStream is = new BufferedInputStream(ClassLoader.getSystemResourceAsStream("petstore3.json"))) {
      Api api = MockDocParser.parse(is);
      Assert.assertNotNull(api);
    }
  }
}
