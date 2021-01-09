package cloud.unionj.generator.backend.springboot;

import cloud.unionj.generator.DefaultGenerator;
import cloud.unionj.generator.GeneratorUtils;
import cloud.unionj.generator.backend.docparser.entity.Vo;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static cloud.unionj.generator.backend.springboot.Constants.OUTPUT_DIR;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator
 * date 2020/11/22
 */
public class VoJavaGenerator extends DefaultGenerator {

  private Vo vo;
  private String outputDir;
  private String packageName;

  public VoJavaGenerator(Vo vo, String packageName, String outputDir) {
    this.vo = vo;
    this.packageName = packageName;
    this.outputDir = outputDir;
  }

  @Override
  public Map<String, Object> getInput() {
    Map<String, Object> input = new HashMap<>();
    input.put("packageName", this.packageName);
    input.put("name", StringUtils.capitalize(this.vo.getName()));
    input.put("properties", this.vo.getProperties());
    input.put("enumTypes", this.vo.getEnumTypes());
    input.put("imports", this.vo.getImports());
    return input;
  }

  @Override
  public String getTemplate() {
    return OUTPUT_DIR + "/vo.java.ftl";
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir) + File.separator + StringUtils.capitalize(this.vo.getName()).replaceAll("<.*>", "") + ".java";
  }

}
