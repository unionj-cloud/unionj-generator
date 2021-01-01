package cloud.unionj.generator.openapi3.model.paths;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.model
 *  date 2020/12/14
 */
@Data
public class Path {

  @JsonIgnore
  private String endpoint;

  private Operation get;

  private Operation post;

  private Operation put;

  private Operation delete;

  // TODO
  private List<Parameter> parameters;
}
