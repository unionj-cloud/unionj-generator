package com.tsingxiao.unionj.generator.server;

import com.tsingxiao.unionj.generator.GeneratorUtils;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator
 * @date:2020/11/22
 */
public class SpringbootProjectGenerator extends ServerGenerator {

  private String doc;
  private String projectName;
  private String outputDir;

  public static final class Builder {
    private String doc;
    private String projectName;
    private String outputDir = OUTPUT_DIR;

    public Builder(String projectName) {
      this.projectName = projectName;
    }

    public Builder outputDir(String outputDir) {
      this.outputDir = outputDir;
      return this;
    }

    public Builder doc(String doc) {
      this.doc = doc;
      return this;
    }

    public SpringbootProjectGenerator build() {
      SpringbootProjectGenerator vueProjectGenerator = new SpringbootProjectGenerator();
      vueProjectGenerator.projectName = this.projectName;
      vueProjectGenerator.outputDir = this.outputDir;
      vueProjectGenerator.doc = this.doc;
      return vueProjectGenerator;
    }
  }

  @Override
  public Map<String, Object> getInput() {
    return null;
  }

  @Override
  public String getTemplate() {
    return null;
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir);
  }

  @SneakyThrows
  @Override
  public String generate() {
//    File file = new File(SpringbootProjectGenerator.class.getClassLoader().getResource(OUTPUT_DIR).getPath());
//    File dest = new File(getOutputFile());
//    FileUtils.copyDirectory(file, dest);

    String outputFile = GeneratorUtils.getOutputDir("output") + File.separator + this.projectName + "_springboot.zip";
    String sourceFile = getOutputFile();
    return GeneratorUtils.generateFolder(sourceFile, outputFile);
  }
}
