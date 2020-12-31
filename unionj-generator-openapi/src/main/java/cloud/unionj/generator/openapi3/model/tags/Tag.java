package cloud.unionj.generator.openapi3.model.tags;

import cloud.unionj.generator.openapi3.model.ExternalDocs;
import lombok.Data;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.openapi3.model
 * @date:2020/12/14
 */
@Data
public class Tag {

  private String name;
  private String description;

  // TODO
  private ExternalDocs externalDocs;
}
