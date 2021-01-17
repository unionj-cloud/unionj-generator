package cloud.unionj.generator.backend.springboot;

import java.io.File;

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

    this.outputDir = OUTPUT_DIR + File.separator + "vo";
    this.artifactId = this.parentArtifactId + "-vo";

    this.hasParent = true;
  }

  @Override
  public String getTemplate() {
    return OUTPUT_DIR + "/vopom.xml.ftl";
  }

}
