package cloud.unionj.generator.openapi3.model.paths;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.model
 *  date 2020/12/14
 */
@Data
public class Response {

  @JsonProperty("$ref")
  private String ref;

  // REQUIRED
  private String description;

  private Content content;

  // TODO
  private Map<String, Header> headers;

  // TODO
  private Map<String, Link> links;

}
