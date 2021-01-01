package cloud.unionj.generator.openapi3.model.paths;

import com.fasterxml.jackson.annotation.JsonProperty;
import cloud.unionj.generator.openapi3.model.Schema;
import lombok.Data;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.model
 * @date 2020/12/14
 */
@Data
public class Header {

  @JsonProperty("$ref")
  private String ref;

  private String description;
  private boolean required;
  private boolean deprecated;
  private Object example;
  public Schema schema;

}
