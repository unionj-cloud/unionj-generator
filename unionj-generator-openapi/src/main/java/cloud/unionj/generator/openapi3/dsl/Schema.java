package cloud.unionj.generator.openapi3.dsl;

import cloud.unionj.generator.openapi3.expression.Openapi3Builder;
import cloud.unionj.generator.openapi3.expression.SchemaBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Consumer;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.dsl
 * date 2020/12/16
 */
public class Schema extends Openapi3 {
  public static cloud.unionj.generator.openapi3.model.Schema schema(Consumer<SchemaBuilder> consumer) {
    if (openapi3Builder == null) {
      openapi3Builder = new Openapi3Builder();
    }
    SchemaBuilder schemaBuilder = new SchemaBuilder(openapi3Builder);
    consumer.accept(schemaBuilder);
    cloud.unionj.generator.openapi3.model.Schema schema = schemaBuilder.build();
    if (StringUtils.isNotBlank(schema.getTitle())) {
      openapi3Builder.components(schema.getTitle(), schema);
    }
    return schema;
  }
}
