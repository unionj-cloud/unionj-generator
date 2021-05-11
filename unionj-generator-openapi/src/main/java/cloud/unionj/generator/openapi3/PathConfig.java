package cloud.unionj.generator.openapi3;

import cloud.unionj.generator.openapi3.model.Schema;
import cloud.unionj.generator.openapi3.model.paths.Parameter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Data;

/**
 * @author created by wubin
 * @version v0.0.1
 * description: cloud.unionj.generator.openapi3
 * date:2021/5/11
 */
@Builder
@Data
public class PathConfig {
  private String summary;
  private String[] tags;
  private Parameter[] parameters;
  private Schema reqSchema;
  private Schema respSchema;
  private SchemaType reqSchemaType;
  private SchemaType respSchemaType;

  public enum SchemaType {
    JSON("json"),
    FORMDATA("formdata"),
    STREAM("stream");

    private String value;

    SchemaType(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SchemaType fromValue(String text) {
      for (SchemaType b : SchemaType.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
}
