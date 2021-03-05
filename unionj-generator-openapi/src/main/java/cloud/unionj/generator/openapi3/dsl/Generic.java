package cloud.unionj.generator.openapi3.dsl;

import cloud.unionj.generator.openapi3.expression.GenericBuilder;

import java.util.function.Consumer;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.dsl
 * date 2020/12/16
 */
public class Generic extends Openapi3 {
  public static cloud.unionj.generator.openapi3.model.Generic generic(Consumer<GenericBuilder> consumer) {
    GenericBuilder genericBuilder = new GenericBuilder();
    consumer.accept(genericBuilder);
    cloud.unionj.generator.openapi3.model.Generic generic = genericBuilder.build();
    openapi3Builder.components(generic.getTitle(), generic);
    return generic;
  }
}
