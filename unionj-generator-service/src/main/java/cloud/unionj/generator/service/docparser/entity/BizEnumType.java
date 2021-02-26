package cloud.unionj.generator.service.docparser.entity;

import lombok.Data;

import java.util.List;

/**
 * @author created by wubin
 * @version v0.0.1
 * description: cloud.unionj.generator.service.docparser.entity
 * date:2021/2/26
 */
@Data
public class BizEnumType {

  private List<BizEnum> enums;
  private String name;

  public BizEnumType(List<BizEnum> enums, String name) {
    this.enums = enums;
    this.name = name;
  }

}
