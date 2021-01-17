package cloud.unionj.generator.backend.springboot;

import cloud.unionj.generator.DefaultGenerator;
import cloud.unionj.generator.GeneratorUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static cloud.unionj.generator.backend.springboot.Constants.MAVEN_POM;
import static cloud.unionj.generator.backend.springboot.Constants.OUTPUT_DIR;

/**
 * @author created by tqccc
 * @version v0.0.1
 * description: cloud.unionj.generator.backend.springboot
 * date:2021/1/17
 */
public abstract class BasePomGenerator extends DefaultGenerator {

  protected String outputDir;

  protected String groupId;

  protected String artifactId;

  protected String version;

  protected Boolean hasParent;

  protected String parentGroupId;

  protected String parentArtifactId;

  protected String parentVersion;


  public BasePomGenerator outputDir(String outputDir) {
    this.outputDir = outputDir;
    return this;
  }

  public BasePomGenerator outputDirAsArtifactId() {
    this.outputDir = this.artifactId;
    return this;
  }

  public BasePomGenerator groupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

  public BasePomGenerator groupIdAsParent() {
    this.groupId = this.parentGroupId;
    return this;
  }

  public BasePomGenerator artifactId(String artifactId) {
    this.artifactId = artifactId;
    return this;
  }

  public BasePomGenerator version(String version) {
    this.version = version;
    return this;
  }

  public BasePomGenerator versionAsParent() {
    this.version = parentVersion;
    return this;
  }

  public BasePomGenerator hasParent(Boolean hasParent) {
    this.hasParent = hasParent;
    return this;
  }

  public BasePomGenerator parentGroupId(String parentGroupId) {
    this.parentGroupId = parentGroupId;
    return this;
  }

  public BasePomGenerator parentArtifactId(String parentArtifactId) {
    this.parentArtifactId = parentArtifactId;
    return this;
  }

  public BasePomGenerator parentVersion(String parentVersion) {
    this.parentVersion = parentVersion;
    return this;
  }

  public <T extends BasePomGenerator> T build() {
    return (T) this;
  }

  public BasePomGenerator() {
    this.outputDir = OUTPUT_DIR;
    this.hasParent = false;
    this.parentGroupId = "cloud.unionj.demo";
    this.parentArtifactId = "unionj-demo";
    this.parentVersion = "1.0.0-SNAPSHOT";

    this.groupId = this.parentGroupId;
    this.artifactId = this.parentArtifactId + "-unknown";
    this.version = this.parentVersion;
  }

  @Override
  public Map<String, Object> getInput() {
    Map<String, Object> input = new HashMap<>(8);

    input.put("hasParent", hasParent);
    input.put("parentGroupId", parentGroupId);
    input.put("parentArtifactId", parentArtifactId);
    input.put("parentVersion", parentVersion);

    input.put("groupId", groupId);
    input.put("artifactId", artifactId);
    input.put("version", version);

    return input;
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir) + File.separator + MAVEN_POM;
  }


}
