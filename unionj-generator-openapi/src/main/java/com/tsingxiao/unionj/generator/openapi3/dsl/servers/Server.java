package com.tsingxiao.unionj.generator.openapi3.dsl.servers;

import com.tsingxiao.unionj.generator.openapi3.dsl.Openapi3;
import com.tsingxiao.unionj.generator.openapi3.expression.servers.ServerBuilder;

import java.util.function.Consumer;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class Server extends Openapi3 {

  protected static ServerBuilder serverBuilder;

  public static void server(Consumer<ServerBuilder> consumer) {
    serverBuilder = new ServerBuilder();
    consumer.accept(serverBuilder);
    com.tsingxiao.unionj.generator.openapi3.model.servers.Server server = serverBuilder.build();
    openapi3Builder.servers(server);
  }

}
