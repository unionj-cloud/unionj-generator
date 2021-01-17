package cloud.unionj.generator.backend.springboot;

import static cloud.unionj.generator.backend.springboot.Constants.OUTPUT_DIR;

/**
 * @author created by tqccc
 * @version v0.0.1
 * description: cloud.unionj.generator.backend.springboot
 * date:2021/1/12
 */
public class VoPomGenerator extends BasePomGenerator {

  public VoPomGenerator() {
    super();
  }

  public static VoPomGenerator newInstance() {
    return new VoPomGenerator();
  }

  @Override
  public String getTemplate() {
    return OUTPUT_DIR + "/vopom.xml.ftl";
  }

}
