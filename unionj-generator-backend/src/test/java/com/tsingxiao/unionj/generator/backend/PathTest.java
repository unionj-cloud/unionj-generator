package com.tsingxiao.unionj.generator.backend;

import com.tsingxiao.unionj.generator.backend.docparser.BackendDocParser;
import com.tsingxiao.unionj.generator.backend.docparser.entity.Backend;
import com.tsingxiao.unionj.generator.backend.springboot.SpringbootFolderGenerator;
import com.tsingxiao.unionj.generator.openapi3.dsl.SchemaHelper;
import com.tsingxiao.unionj.generator.openapi3.model.Openapi3;
import org.junit.Test;

import java.io.IOException;

import static com.tsingxiao.unionj.generator.openapi3.dsl.Openapi3.openapi3;
import static com.tsingxiao.unionj.generator.openapi3.dsl.info.Info.info;
import static com.tsingxiao.unionj.generator.openapi3.dsl.servers.Server.server;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl.paths
 * @date:2020/12/16
 */
public class PathTest {

  @Test
  public void TestPath() throws IOException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试");
        ib.version("v1.0.0");
      });

      server(sb -> {
        sb.url("http://www.unionj.com");
      });

      SchemaHelper.batchImport(Components.class);
    });
    Backend backend = BackendDocParser.parse(openapi3);
    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
    springbootFolderGenerator.generate();
  }

  @Test
  public void Test1() {
    String name = "ResultDTO<List<User<Integer>>>.java";
    String s = name.replaceAll("<.*>", "");
    System.out.println(s);
  }
}
