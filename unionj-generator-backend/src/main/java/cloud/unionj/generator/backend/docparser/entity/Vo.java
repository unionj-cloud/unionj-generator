package cloud.unionj.generator.backend.docparser.entity;

import cloud.unionj.generator.Utils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.backend.docparser.entity
 * date 2020/12/21
 */
@Data
public class Vo {

  public void setName(String name) {
    this.name = Utils.cleanClassName(name);
  }

  private String name;
  private String description;
  private List<VoProperty> properties;
  private List<VoEnumType> enumTypes;
  private List<String> imports;
  private String dummy;
  private boolean output = true;

  public boolean isDummy() {
    return StringUtils.isNotBlank(this.dummy);
  }

}
