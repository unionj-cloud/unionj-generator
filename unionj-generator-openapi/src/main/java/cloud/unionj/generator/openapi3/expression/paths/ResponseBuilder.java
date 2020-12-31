package cloud.unionj.generator.openapi3.expression.paths;

import cloud.unionj.generator.openapi3.model.paths.Content;
import cloud.unionj.generator.openapi3.model.paths.Response;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.openapi3.expression.paths
 * @date:2020/12/19
 */
public class ResponseBuilder {

  private Response response;

  public ResponseBuilder() {
    this.response = new Response();
  }

  public void description(String description) {
    this.response.setDescription(description);
  }

  public void content(Content content) {
    this.response.setContent(content);
  }

  public Response build() {
    return this.response;
  }
}
