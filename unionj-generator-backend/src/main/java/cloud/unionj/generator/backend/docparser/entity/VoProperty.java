package cloud.unionj.generator.backend.docparser.entity;

import cloud.unionj.generator.Utils;
import cloud.unionj.generator.openapi3.dsl.SchemaHelper;
import cloud.unionj.generator.openapi3.model.Schema;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.backend.docparser.entity
 *  date 2020/12/21
 */
@Data
public class VoProperty {
  private String name;
  private String description;
  private String jsonProperty;
  private String type;

  public VoProperty(String name, String jsonProperty, String type) {
    this.name = Utils.cleanPropName(name);
    this.jsonProperty = jsonProperty;
    this.type = type;
  }

  public VoProperty(String name, String jsonProperty, String type, String description) {
    this.name = Utils.cleanPropName(name);
    this.jsonProperty = jsonProperty;
    this.type = type;
    this.description = StringUtils.replaceAll(StringUtils.trim(description), "\n", "");
  }

  public VoProperty(String name, String jsonProperty, Schema type) {
    this.name = Utils.cleanPropName(name);
    this.jsonProperty = jsonProperty;
    this.type = type.javaType()
                    .replaceAll(SchemaHelper.LEFT_ARROW, "<")
                    .replaceAll(SchemaHelper.RIGHT_ARROW, ">");
  }

  public VoProperty(String name, String jsonProperty, Schema type, String description) {
    this.name = Utils.cleanPropName(name);
    this.jsonProperty = jsonProperty;
    this.type = type.javaType()
                    .replaceAll(SchemaHelper.LEFT_ARROW, "<")
                    .replaceAll(SchemaHelper.RIGHT_ARROW, ">");
    this.description = StringUtils.replaceAll(StringUtils.trim(description), "\n", "");
  }
}
