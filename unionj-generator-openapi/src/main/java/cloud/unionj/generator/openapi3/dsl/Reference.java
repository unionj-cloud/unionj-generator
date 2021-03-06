package cloud.unionj.generator.openapi3.dsl;

import cloud.unionj.generator.openapi3.expression.ReferenceBuilder;
import cloud.unionj.generator.openapi3.model.Schema;

import java.util.function.Consumer;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.dsl
 * date 2020/12/16
 */
public class Reference {

  public static Schema reference(Consumer<ReferenceBuilder> consumer) {
    ReferenceBuilder referenceBuilder = new ReferenceBuilder();
    consumer.accept(referenceBuilder);
    Schema schema = referenceBuilder.build();
    return schema;
  }
}
