package cloud.unionj.generator.openapi3.dsl;

import cloud.unionj.generator.openapi3.expression.SchemaBuilder;

import java.util.function.Consumer;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.dsl
 * @date 2020/12/16
 */
public class Schema {
  public static cloud.unionj.generator.openapi3.model.Schema schema(Consumer<SchemaBuilder> consumer) {
    SchemaBuilder schemaBuilder = new SchemaBuilder();
    consumer.accept(schemaBuilder);
    cloud.unionj.generator.openapi3.model.Schema schema = schemaBuilder.build();
    return schema;
  }
}
