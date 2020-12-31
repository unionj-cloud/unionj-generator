package cloud.unionj.generator.service;

import cloud.unionj.generator.GeneratorUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator
 * @date:2020/11/22
 */
public class BizServiceTsGenerator extends ServiceGenerator {

  private String serverName;
  private String outputDir = OUTPUT_DIR;

  public BizServiceTsGenerator(String serverName) {
    this.serverName = serverName;
  }

  public BizServiceTsGenerator(String serverName, String outputDir) {
    this.serverName = serverName;
    this.outputDir = outputDir;
  }

  @Override
  public Map<String, Object> getInput() {
    Map<String, Object> input = new HashMap<>();
    input.put("serverName", this.serverName);
    return input;
  }

  @Override
  public String getTemplate() {
    return OUTPUT_DIR + File.separator + "BizService.ts.ftl";
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir) + File.separator + "BizService.ts";
  }

}
