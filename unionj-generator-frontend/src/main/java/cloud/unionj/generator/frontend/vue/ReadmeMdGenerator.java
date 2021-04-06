package cloud.unionj.generator.frontend.vue;

import cloud.unionj.generator.GeneratorUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator
 *  date 2020/11/22
 */
public class ReadmeMdGenerator extends VueGenerator {

  private String projectName;
  private String outputDir = OUTPUT_DIR;

  public ReadmeMdGenerator(String projectName) {
    this.projectName = projectName;
  }

  public ReadmeMdGenerator(String projectName, String outputDir) {
    this.projectName = projectName;
    this.outputDir = outputDir;
  }

  @Override
  public Map<String, Object> getInput() {
    Map<String, Object> input = new HashMap<String, Object>();
    input.put("projectName", this.projectName);
    return input;
  }

  @Override
  public String getTemplate() {
    return OUTPUT_DIR + "/README.md.ftl";
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir) + File.separator + "README.md";
  }

}
