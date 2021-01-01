package cloud.unionj.generator.openapi3.model.info;

import lombok.Data;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.model
 * @date 2020/12/14
 */
@Data
public class Info {
  private String title;
  private String description;
  private String termsOfService;
  private Contact contact;
  private License license;
  private String version;
}
