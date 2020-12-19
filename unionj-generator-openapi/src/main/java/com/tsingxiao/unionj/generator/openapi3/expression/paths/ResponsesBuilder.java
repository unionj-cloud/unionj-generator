package com.tsingxiao.unionj.generator.openapi3.expression.paths;

import com.tsingxiao.unionj.generator.openapi3.model.paths.Response;
import com.tsingxiao.unionj.generator.openapi3.model.paths.Responses;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.expression.paths
 * @date:2020/12/19
 */
public class ResponsesBuilder {

  private Responses responses;

  public ResponsesBuilder() {
    this.responses = new Responses();
  }

  public void response200(Response response200) {
    this.responses.setResponse200(response200);
  }

  public void response400(Response response400) {
    this.responses.setResponse400(response400);
  }

  public void response404(Response response404) {
    this.responses.setResponse404(response404);
  }

  public void response405(Response response405) {
    this.responses.setResponse405(response405);
  }

  public void defaultResp(Response defaultResp) {
    this.responses.setDefaultResp(defaultResp);
  }

  public Responses build() {
    return this.responses;
  }
}
