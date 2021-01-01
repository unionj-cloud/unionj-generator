package cloud.unionj.generator.openapi3.expression.paths;

import cloud.unionj.generator.openapi3.model.Schema;
import cloud.unionj.generator.openapi3.model.paths.MediaType;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.expression.paths
 *  date 2020/12/19
 */
public class MediaTypeBuilder {

  private MediaType mediaType;

  public MediaTypeBuilder() {
    this.mediaType = new MediaType();
  }

  public void schema(Schema schema) {
    this.mediaType.setSchema(schema);
  }

  public void example(Object example) {
    this.mediaType.setExample(example);
  }

  public MediaType build() {
    return this.mediaType;
  }


}
