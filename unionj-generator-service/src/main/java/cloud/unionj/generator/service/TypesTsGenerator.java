package cloud.unionj.generator.service;

import cloud.unionj.generator.GeneratorUtils;
import cloud.unionj.generator.service.docparser.entity.BizType;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator
 * @date:2020/11/22
 */
public class TypesTsGenerator extends ServiceGenerator {

  private List<BizType> types;
  private String outputDir = OUTPUT_DIR;

  public TypesTsGenerator(List<BizType> types) {
    this.types = types;
  }

  public TypesTsGenerator(List<BizType> types, String outputDir) {
    this.types = types;
    this.outputDir = outputDir;
  }

  @Override
  public Map<String, Object> getInput() {
    Map<String, Object> input = new HashMap<>();
    input.put("types", this.types);
    return input;
  }

  @Override
  public String getTemplate() {
    return OUTPUT_DIR + File.separator + "types.ts.ftl";
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir) + File.separator + "types.ts";
  }

}
