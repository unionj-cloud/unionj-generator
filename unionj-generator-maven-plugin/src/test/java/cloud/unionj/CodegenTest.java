package cloud.unionj;

import cloud.unionj.generator.backend.docparser.BackendDocParser;
import cloud.unionj.generator.backend.docparser.entity.Backend;
import junit.framework.TestCase;
import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

/**
 * @author: created by wubin
 * cloud.unionj
 * 2022/7/17
 */
public class CodegenTest extends TestCase {

  @SneakyThrows
  public void testExecute() {
    URL url = new URL(
        "http://qylz:1234@api.gateway.c92358f369e164c2bbbdee14238b6e9a6.cn-beijing.alicontainer" + ".com/lottery-svc" +
            "/go-doudou/openapi.json");
    URLConnection uc = url.openConnection();
    String basicAuth = "Basic " + new String(Base64.getEncoder()
                                                   .encode(url.getUserInfo()
                                                              .getBytes()));
    uc.setRequestProperty("Authorization", basicAuth);
    try (BufferedInputStream in = new BufferedInputStream(uc.getInputStream())) {
      Backend backend = BackendDocParser.parse(in);
      System.out.println(backend);
    }
  }
}
