package cloud.unionj.generator.openapi3.model.paths;

import lombok.Data;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.model
 * @date 2020/12/14
 */
@Data
public class RequestBody {

  private String description;
  private Content content;
  private boolean required;


}
