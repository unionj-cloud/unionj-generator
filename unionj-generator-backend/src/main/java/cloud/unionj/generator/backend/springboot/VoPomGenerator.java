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
