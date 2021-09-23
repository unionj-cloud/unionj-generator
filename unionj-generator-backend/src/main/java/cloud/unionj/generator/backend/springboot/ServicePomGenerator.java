package cloud.unionj.generator.backend.springboot;

import java.io.File;
import java.util.Map;

import static cloud.unionj.generator.backend.springboot.Constants.OUTPUT_DIR;

/**
 * @author created by tqccc
 * @version v0.0.1
 * description: cloud.unionj.generator.backend.springboot
 * date:2021/1/17
 */
public class ServicePomGenerator extends BasePomGenerator<ServicePomGenerator> {

  protected String voGroupId;

  protected String voArtifactId;

  protected String voVersion;

  protected String protoGroupId;

  protected String protoArtifactId;

  protected String protoVersion;

  public ServicePomGenerator() {
    super();

    this.outputDir = OUTPUT_DIR + File.separator + "service";
    this.artifactId = this.parentArtifactId + "-service";

    this.hasParent = true;

    this.voGroupId = this.parentGroupId;
    this.voArtifactId = this.parentArtifactId + "-vo";
    this.voVersion = this.parentVersion;

    this.protoGroupId = this.parentGroupId;
    this.protoArtifactId = this.parentArtifactId + "-proto";
    this.protoVersion = this.parentVersion;
  }

  public ServicePomGenerator(String serviceDir) {
    super();

    this.outputDir = serviceDir;
    this.artifactId = this.parentArtifactId + "-service";

    this.hasParent = true;

    this.voGroupId = this.parentGroupId;
    this.voArtifactId = this.parentArtifactId + "-vo";
    this.voVersion = this.parentVersion;

    this.protoGroupId = this.parentGroupId;
    this.protoArtifactId = this.parentArtifactId + "-proto";
    this.protoVersion = this.parentVersion;
  }

  public ServicePomGenerator voGroupId(String voGroupId) {
    this.voGroupId = voGroupId;
    return this;
  }

  public ServicePomGenerator voGroupIdAsParent() {
    this.voGroupId = this.parentGroupId;
    return this;
  }

  public ServicePomGenerator voArtifactId(String voArtifactId) {
    this.voArtifactId = voArtifactId;
    return this;
  }

  public ServicePomGenerator voVersion(String voVersion) {
    this.voVersion = voVersion;
    return this;
  }

  public ServicePomGenerator voVersionAsParent() {
    this.voVersion = this.parentVersion;
    return this;
  }

  public ServicePomGenerator protoGroupId(String protoGroupId) {
    this.protoGroupId = protoGroupId;
    return this;
  }

  public ServicePomGenerator protoGroupIdAsParent() {
    this.protoGroupId = this.parentGroupId;
    return this;
  }

  public ServicePomGenerator protoArtifactId(String protoArtifactId) {
    this.protoArtifactId = protoArtifactId;
    return this;
  }

  public ServicePomGenerator protoVersion(String protoVersion) {
    this.protoVersion = protoVersion;
    return this;
  }

  public ServicePomGenerator protoVersionAsParent() {
    this.protoVersion = this.parentVersion;
    return this;
  }

  @Override
  public Map<String, Object> getInput() {
    Map<String, Object> input = super.getInput();

    input.put("voGroupId", voGroupId);
    input.put("voArtifactId", voArtifactId);
    input.put("voVersion", voVersion);

    input.put("protoGroupId", protoGroupId);
    input.put("protoArtifactId", protoArtifactId);
    input.put("protoVersion", protoVersion);

    return input;
  }

  @Override
  public String getTemplate() {
    return OUTPUT_DIR + "/servicepom.xml.ftl";
  }

}
