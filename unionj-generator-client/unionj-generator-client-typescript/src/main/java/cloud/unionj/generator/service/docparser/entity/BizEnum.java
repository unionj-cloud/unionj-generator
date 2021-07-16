package cloud.unionj.generator.service.docparser.entity;

import lombok.Data;

/**
 * @author created by wubin
 * @version v0.0.1
 * description: cloud.unionj.generator.service.docparser.entity
 * date:2021/2/26
 */
@Data
public class BizEnum {
  private String name;
  private String value;

  public BizEnum(String name, String value) {
    this.name = name;
    this.value = value;
  }
}
