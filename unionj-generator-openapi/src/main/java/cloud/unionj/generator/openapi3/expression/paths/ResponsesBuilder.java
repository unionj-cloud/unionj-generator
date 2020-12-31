package cloud.unionj.generator.openapi3.expression.paths;

import cloud.unionj.generator.openapi3.model.paths.Response;
import cloud.unionj.generator.openapi3.model.paths.Responses;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.openapi3.expression.paths
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

  public void response401(Response response401) {
    this.responses.setResponse401(response401);
  }

  public void response403(Response response403) {
    this.responses.setResponse403(response403);
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
