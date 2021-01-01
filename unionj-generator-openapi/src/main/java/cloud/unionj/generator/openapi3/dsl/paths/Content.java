package cloud.unionj.generator.openapi3.dsl.paths;

import cloud.unionj.generator.openapi3.expression.paths.ContentBuilder;

import java.util.function.Consumer;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.dsl
 *  date 2020/12/16
 */
public class Content {

  protected static ContentBuilder contentBuilder;

  public static cloud.unionj.generator.openapi3.model.paths.Content content(Consumer<ContentBuilder> consumer) {
    contentBuilder = new ContentBuilder();
    consumer.accept(contentBuilder);
    cloud.unionj.generator.openapi3.model.paths.Content content = contentBuilder.build();
    return content;
  }
}
