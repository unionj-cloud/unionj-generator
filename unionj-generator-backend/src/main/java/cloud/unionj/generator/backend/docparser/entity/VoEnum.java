package cloud.unionj.generator.backend.docparser.entity;

import lombok.Data;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.backend.docparser.entity
 * @date:2020/12/21
 */
@Data
public class VoEnum {

  private String name;
  private String value;

  public VoEnum(String name, String value) {
    this.name = name;
    this.value = value;
  }
}
