package cloud.unionj.generator.backend.docparser.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.backend.docparser.entity
 * @date:2020/12/21
 */
@Data
public class Vo {

  private String name;
  private List<VoProperty> properties;
  private List<VoEnumType> enumTypes;
  private List<String> imports;
  private boolean output = true;

}
