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
 * date:2021/1/12
 */
public class VoPomGenerator extends DefaultGenerator {

  private String outputDir;

  private String groupId;

  private String artifactId;

  private String version;

  private Boolean hasParent;

  private String parentGroupId;

  private String parentArtifactId;

  private String parentVersion;

  private VoPomGenerator() {
  }

  public static class Builder {
    private String outputDir;
    private String groupId;
    private String artifactId;
    private String version;

    private Boolean hasParent;
    private String parentGroupId;
    private String parentArtifactId;
    private String parentVersion;

    public Builder outputDir(String outputDir) {
      this.outputDir = outputDir;
      return this;
    }

    public Builder outputDirAsArtifactId() {
      this.outputDir = this.artifactId;
      return this;
    }

    public Builder groupId(String groupId) {
      this.groupId = groupId;
      return this;
    }

    public Builder groupIdAsParent() {
      this.groupId = this.parentGroupId;
      return this;
    }

    public Builder artifactId(String artifactId) {
      this.artifactId = artifactId;
      return this;
    }

    public Builder version(String version) {
      this.version = version;
      return this;
    }

    public Builder versionAsParent() {
      this.version = parentVersion;
      return this;
    }

    public Builder hasParent(Boolean hasParent) {
      this.hasParent = hasParent;
      return this;
    }

    public Builder parentGroupId(String parentGroupId) {
      this.parentGroupId = parentGroupId;
      return this;
    }

    public Builder parentArtifactId(String parentArtifactId) {
      this.parentArtifactId = parentArtifactId;
      return this;
    }

    public Builder parentVersion(String parentVersion) {
      this.parentVersion = parentVersion;
      return this;
    }

    public Builder() {
      this.outputDir = OUTPUT_DIR + File.separator + "vo";
      this.groupId = "cloud.unionj.demo";
      this.artifactId = "unionj-demo-vo";
      this.version = "1.0.0-SNAPSHOT";

      this.hasParent = true;
      this.parentGroupId = this.groupId;
      this.parentArtifactId = "unionj-demo";
      this.parentVersion = this.version;
    }

    public VoPomGenerator build() {
      VoPomGenerator generator = new VoPomGenerator();
      generator.outputDir = this.outputDir;
      generator.groupId = this.groupId;
      generator.artifactId = this.artifactId;
      generator.version = this.version;

      generator.hasParent = this.hasParent;
      generator.parentGroupId = this.parentGroupId;
      generator.parentArtifactId = this.parentArtifactId;
      generator.parentVersion = this.parentVersion;

      return generator;
    }
  }

  public static Builder builder() {
    return new Builder();
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
  public String getTemplate() {
    return OUTPUT_DIR + "/vopom.xml.ftl";
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir) + File.separator + MAVEN_POM;
  }
}
