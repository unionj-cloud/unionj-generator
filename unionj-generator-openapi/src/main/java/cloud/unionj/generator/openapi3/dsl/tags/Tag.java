package cloud.unionj.generator.openapi3.dsl.tags;

import cloud.unionj.generator.openapi3.dsl.Openapi3;
import cloud.unionj.generator.openapi3.expression.tags.TagBuilder;

import java.util.function.Consumer;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.dsl
 *  date 2020/12/14
 */
public class Tag extends Openapi3 {

  protected static TagBuilder tagBuilder;

  public static void tag(Consumer<TagBuilder> consumer) {
    tagBuilder = new TagBuilder();
    consumer.accept(tagBuilder);
    cloud.unionj.generator.openapi3.model.tags.Tag tag = tagBuilder.build();
    openapi3Builder.tags(tag);
  }
}
