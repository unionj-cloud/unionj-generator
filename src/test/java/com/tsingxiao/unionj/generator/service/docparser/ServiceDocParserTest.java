package com.tsingxiao.unionj.generator.service.docparser;

import com.tsingxiao.unionj.generator.service.docparser.entity.BizProperty;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizRouter;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizServer;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.service.docparser
 * @date:2020/11/27
 */
public class ServiceDocParserTest {

  @Test
  public void parse() {
    String testFilePath = ServiceDocParserTest.class.getClassLoader().getResource("petstore3.json").getPath();
    ServiceDocParser docParser = new ServiceDocParser(testFilePath);
    BizServer bizServer = docParser.parse();
    Assert.assertNotNull(bizServer);
    List<BizService> services = bizServer.getServices();
    for (BizService bizService : services) {
      List<BizRouter> routers = bizService.getRouters();
      for (BizRouter bizRouter : routers) {
        String httpMethod = bizRouter.getHttpMethod();
        Assert.assertNotNull(httpMethod);
        if (httpMethod.toLowerCase().equals("post") && bizRouter.getEndpoint().equals("/hall/event/list")) {
          BizProperty reqBody = bizRouter.getReqBody();
          Assert.assertNotNull(reqBody);
          return;
        }
      }
    }
  }
}
