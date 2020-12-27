package com.tsingxiao.unionj.generator.openapi3.expression.servers;


import com.tsingxiao.unionj.generator.openapi3.model.servers.Server;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.expression
 * @date:2020/12/14
 */
public class ServerBuilder {

  private Server server;

  public ServerBuilder() {
    this.server = new Server();
  }

  public void url(String url) {
    this.server.setUrl(url);
  }

  public Server build() {
    return this.server;
  }

}
