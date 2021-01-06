package cloud.unionj.generator.openapi3.model.paths;

import cloud.unionj.generator.openapi3.model.Schema;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.model
 * date 2020/12/14
 */
@Data
public class Parameter {

  @JsonProperty("$ref")
  private String ref;

  private String name;

  public enum InEnum {
    QUERY("query"),
    PATH("path");

    private String value;

    InEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static InEnum fromValue(String text) {
      for (InEnum b : InEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  
  private InEnum in;
  private String description;
  private boolean required;
  private boolean deprecated;
  private Object example;
  public Schema schema;

  // TODO
  private String style;

  // TODO
  private boolean explode;

  // TODO
  private boolean allowReserved;

  // TODO
  private Content content;


}
