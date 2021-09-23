package cloud.unionj.generator.backend.springboot;

import java.io.File;
import java.util.Map;

import static cloud.unionj.generator.backend.springboot.Constants.OUTPUT_DIR;

public class ControllerPomGenerator extends BasePomGenerator<ControllerPomGenerator> {

  protected String protoGroupId;

  protected String protoArtifactId;

  protected String protoVersion;

  protected String serviceGroupId;

  protected String serviceArtifactId;

  protected String serviceVersion;

  public ControllerPomGenerator() {
    super();

    this.outputDir = OUTPUT_DIR + File.separator + "controller";
    this.artifactId = this.parentArtifactId + "-controller";

    this.hasParent = true;

    this.protoGroupId = this.parentGroupId;
    this.protoArtifactId = this.parentArtifactId + "-proto";
    this.protoVersion = this.parentVersion;

    this.serviceGroupId = this.parentGroupId;
    this.serviceArtifactId = this.parentArtifactId + "-service";
    this.serviceVersion = this.parentVersion;
  }

  public ControllerPomGenerator(String controllerDir) {
    super();

    this.outputDir = controllerDir;
    this.artifactId = this.parentArtifactId + "-controller";

    this.hasParent = true;

    this.protoGroupId = this.parentGroupId;
    this.protoArtifactId = this.parentArtifactId + "-proto";
    this.protoVersion = this.parentVersion;

    this.serviceGroupId = this.parentGroupId;
    this.serviceArtifactId = this.parentArtifactId + "-service";
    this.serviceVersion = this.parentVersion;
  }

  public ControllerPomGenerator protoGroupId(String protoGroupId) {
    this.protoGroupId = protoGroupId;
    return this;
  }

  public ControllerPomGenerator protoGroupIdAsParent() {
    this.protoGroupId = this.parentGroupId;
    return this;
  }

  public ControllerPomGenerator protoArtifactId(String protoArtifactId) {
    this.protoArtifactId = protoArtifactId;
    return this;
  }

  public ControllerPomGenerator protoVersion(String protoVersion) {
    this.protoVersion = protoVersion;
    return this;
  }

  public ControllerPomGenerator protoVersionAsParent() {
    this.protoVersion = this.parentVersion;
    return this;
  }


  public ControllerPomGenerator serviceGroupId(String serviceGroupId) {
    this.serviceGroupId = serviceGroupId;
    return this;
  }

  public ControllerPomGenerator serviceGroupIdAsParent() {
    this.serviceGroupId = this.parentGroupId;
    return this;
  }

  public ControllerPomGenerator serviceArtifactId(String serviceArtifactId) {
    this.serviceArtifactId = serviceArtifactId;
    return this;
  }

  public ControllerPomGenerator serviceVersion(String serviceVersion) {
    this.serviceVersion = serviceVersion;
    return this;
  }

  public ControllerPomGenerator serviceVersionAsParent() {
    this.serviceVersion = this.parentVersion;
    return this;
  }

  @Override
  public Map<String, Object> getInput() {
    Map<String, Object> input = super.getInput();

    input.put("protoGroupId", protoGroupId);
    input.put("protoArtifactId", protoArtifactId);
    input.put("protoVersion", protoVersion);

    input.put("serviceGroupId", serviceGroupId);
    input.put("serviceArtifactId", serviceArtifactId);
    input.put("serviceVersion", serviceVersion);

    return input;
  }

  @Override
  public String getTemplate() {
    return OUTPUT_DIR + "/controllerpom.xml.ftl";
  }

}
