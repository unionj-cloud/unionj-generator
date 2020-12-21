package com.tsingxiao.unionj.generator.backend.docparser.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.backend.docparser.entity
 * @date:2020/12/21
 */
@Data
public class ProtoRouter {

  private String endpoint;
  private String name;
  private String httpMethod;
  private ProtoProperty reqBody;
  private ProtoProperty respData;
  private List<ProtoProperty> pathParams;
  private List<ProtoProperty> queryParams;

  public ProtoRouter() {
  }

  public ProtoRouter(String endpoint, String name, String httpMethod, ProtoProperty reqBody, ProtoProperty respData, List<ProtoProperty> pathParams, List<ProtoProperty> queryParams) {
    this.endpoint = endpoint;
    this.name = name;
    this.httpMethod = httpMethod;
    this.reqBody = reqBody;
    this.respData = respData;
    this.pathParams = pathParams;
    this.queryParams = queryParams;
  }

  public static class Builder {
    private String endpoint;
    private String name;
    private String httpMethod;
    private ProtoProperty reqBody;
    private ProtoProperty respData;
    private List<ProtoProperty> pathParams;
    private List<ProtoProperty> queryParams;

    public Builder(String endpoint, String name, String httpMethod) {
      this.endpoint = endpoint;
      this.name = name;
      this.httpMethod = httpMethod;
    }

    public Builder reqBody(ProtoProperty reqBody) {
      this.reqBody = reqBody;
      return this;
    }

    public Builder respData(ProtoProperty respData) {
      this.respData = respData;
      return this;
    }

    public Builder pathParams(List<ProtoProperty> pathParams) {
      this.pathParams = pathParams;
      return this;
    }

    public Builder queryParams(List<ProtoProperty> queryParams) {
      this.queryParams = queryParams;
      return this;
    }

    public ProtoRouter build() {
      ProtoRouter router = new ProtoRouter();
      router.endpoint = this.endpoint;
      router.name = this.name;
      router.httpMethod = this.httpMethod;
      router.reqBody = this.reqBody;
      router.respData = this.respData;
      router.pathParams = this.pathParams;
      router.queryParams = this.queryParams;
      return router;
    }
  }
}
