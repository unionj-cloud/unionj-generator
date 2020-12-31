package cloud.unionj.generator.openapi3.expression.paths;

import cloud.unionj.generator.openapi3.model.paths.Content;
import cloud.unionj.generator.openapi3.model.paths.RequestBody;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.openapi3.expression
 * @date:2020/12/14
 */
public class RequestBodyBuilder {

  private RequestBody requestBody;

  public RequestBodyBuilder() {
    this.requestBody = new RequestBody();
  }

  public void content(Content content) {
    this.requestBody.setContent(content);
  }

  public void description(String description) {
    this.requestBody.setDescription(description);
  }

  public void required(boolean required) {
    this.requestBody.setRequired(required);
  }


  public RequestBody build() {
    return this.requestBody;
  }
}
