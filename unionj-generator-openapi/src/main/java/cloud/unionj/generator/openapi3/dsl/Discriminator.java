package cloud.unionj.generator.openapi3.dsl;

import cloud.unionj.generator.openapi3.expression.DiscriminatorBuilder;

import java.util.function.Consumer;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.dsl
 * @date 2020/12/16
 */
public class Discriminator {

  public static cloud.unionj.generator.openapi3.model.Discriminator discriminator(Consumer<DiscriminatorBuilder> consumer) {
    DiscriminatorBuilder discriminatorBuilder = new DiscriminatorBuilder();
    consumer.accept(discriminatorBuilder);
    cloud.unionj.generator.openapi3.model.Discriminator discriminator = discriminatorBuilder.build();
    return discriminator;
  }
}
