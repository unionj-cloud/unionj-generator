package cloud.unionj.generator.backend.springboot;

import java.io.File;

import static cloud.unionj.generator.backend.springboot.Constants.OUTPUT_DIR;

/**
 * @author created by tqccc
 * @version v0.0.1
 * description: cloud.unionj.generator.backend.springboot
 * date:2021/1/17
 */
public class ProtoPomGenerator extends BasePomGenerator {

  protected String voGroupId;

  protected String voArtifactId;

  protected String voVersion;

  public ProtoPomGenerator() {
    super();

    this.outputDir = OUTPUT_DIR + File.separator + "proto";
    this.artifactId = "unionj-demo-proto";

    this.hasParent = true;

    this.voGroupId = this.parentGroupId;
    this.voArtifactId = this.parentArtifactId + "-vo";
    this.voVersion = this.parentVersion;
  }

  @Override
  public String getTemplate() {
    return OUTPUT_DIR + "/protopom.xml.ftl";
  }

}
