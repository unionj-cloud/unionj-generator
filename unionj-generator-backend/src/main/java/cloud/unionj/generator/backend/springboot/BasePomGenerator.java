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
public abstract class BasePomGenerator<T> extends DefaultGenerator {

  protected String outputDir;

  protected String groupId;

  protected String artifactId;

  protected String version;

  protected Boolean hasParent;

  protected String parentGroupId;

  protected String parentArtifactId;

  protected String parentVersion;


  public T outputDir(String outputDir) {
    this.outputDir = outputDir;
    return (T) this;
  }

  public T outputDirAsArtifactId() {
    this.outputDir = this.artifactId;
    return (T) this;
  }

  public T groupId(String groupId) {
    this.groupId = groupId;
    return (T) this;
  }

  public T groupIdAsParent() {
    this.groupId = this.parentGroupId;
    return (T) this;
  }

  public T artifactId(String artifactId) {
    this.artifactId = artifactId;
    return (T) this;
  }

  public T version(String version) {
    this.version = version;
    return (T) this;
  }

  public T versionAsParent() {
    this.version = parentVersion;
    return (T) this;
  }

  public T hasParent(Boolean hasParent) {
    this.hasParent = hasParent;
    return (T) this;
  }

  public T parentGroupId(String parentGroupId) {
    this.parentGroupId = parentGroupId;
    return (T) this;
  }

  public T parentArtifactId(String parentArtifactId) {
    this.parentArtifactId = parentArtifactId;
    return (T) this;
  }

  public T parentVersion(String parentVersion) {
    this.parentVersion = parentVersion;
    return (T) this;
  }

  protected BasePomGenerator() {
    super(false);
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
