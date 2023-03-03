package cloud.unionj.generator.backend.docparser.entity;

import cloud.unionj.generator.Utils;
import lombok.Data;

import java.util.List;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.backend.docparser.entity
 * date 2020/12/21
 */
@Data
public class Proto {

  private List<String> imports;

  public void setName(String name) {
    this.name = Utils.cleanClassName(name);
  }

  private String name;
  private List<ProtoRouter> routers;

  // TODO
  private String base;

}
