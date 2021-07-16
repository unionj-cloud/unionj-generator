package cloud.unionj.generator.service.docparser;

import cloud.unionj.generator.service.docparser.entity.BizRouter;
import cloud.unionj.generator.service.docparser.entity.BizServer;
import cloud.unionj.generator.service.docparser.entity.BizService;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.service.docparser
 *  date 2020/11/27
 */
public class ServiceDocParserTest {

  @Test
  public void parse() throws IOException {
    try (BufferedInputStream is = new BufferedInputStream(new FileInputStream("/home/qylz/workspace/treeyee_openapi3.json"))) {
      BizServer bizServer = ServiceDocParser.parse(is);
      Assert.assertNotNull(bizServer);
      List<BizService> services = bizServer.getServices();
      for (BizService bizService : services) {
        List<BizRouter> routers = bizService.getRouters();
        for (BizRouter bizRouter : routers) {
          String httpMethod = bizRouter.getHttpMethod();
          Assert.assertNotNull(httpMethod);
        }
      }
    }
  }
}
