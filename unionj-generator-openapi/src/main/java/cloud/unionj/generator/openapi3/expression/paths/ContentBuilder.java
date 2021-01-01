package cloud.unionj.generator.openapi3.expression.paths;

import cloud.unionj.generator.openapi3.model.paths.Content;
import cloud.unionj.generator.openapi3.model.paths.MediaType;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.expression
 * @date 2020/12/16
 */
public class ContentBuilder {

  private Content content;

  public ContentBuilder() {
    this.content = new Content();
  }

  public void textPlain(MediaType textPlain) {
    this.content.setTextPlain(textPlain);
  }

  public void applicationJson(MediaType applicationJson) {
    this.content.setApplicationJson(applicationJson);
  }

  public void applicationXWwwFormUrlencoded(MediaType applicationXWwwFormUrlencoded) {
    this.content.setApplicationXWwwFormUrlencoded(applicationXWwwFormUrlencoded);
  }

  public void applicationOctetStream(MediaType applicationOctetStream) {
    this.content.setApplicationOctetStream(applicationOctetStream);
  }

  public void multipartFormData(MediaType multipartFormData) {
    this.content.setMultipartFormData(multipartFormData);
  }

  public Content build() {
    return this.content;
  }
}
