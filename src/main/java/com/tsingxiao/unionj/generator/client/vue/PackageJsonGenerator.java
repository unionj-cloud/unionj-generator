package com.tsingxiao.unionj.generator.client.vue;

import com.tsingxiao.unionj.generator.GeneratorUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator
 * @date:2020/11/22
 */
public class PackageJsonGenerator extends VueGenerator {

  private String projectName;
  private String outputDir = OUTPUT_DIR;

  public PackageJsonGenerator(String projectName) {
    this.projectName = projectName;
  }

  public PackageJsonGenerator(String projectName, String outputDir) {
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
    return OUTPUT_DIR + File.separator + "package.json.ftl";
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir) + File.separator + "package.json";
  }

}
