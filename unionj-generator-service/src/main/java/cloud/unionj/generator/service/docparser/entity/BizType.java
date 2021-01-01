package cloud.unionj.generator.service.docparser.entity;

import lombok.Data;

import java.util.List;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.mock.docparser.entity
 *  date 2020/11/18
 */

/**
 * ReqBody typescript interface representation
 */
@Data
public class BizType {

  private String name;
  private List<BizProperty> properties;

}
