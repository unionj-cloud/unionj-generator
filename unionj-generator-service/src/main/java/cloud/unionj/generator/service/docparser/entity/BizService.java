package cloud.unionj.generator.service.docparser.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.mock.docparser.entity
 * @date:2020/11/18
 */
@Data
public class BizService {
  private String name;
  private List<BizRouter> routers;
  private List<String> types;
}
