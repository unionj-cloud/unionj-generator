package com.tsingxiao.unionj.generator.backend.springboot;

import com.tsingxiao.unionj.generator.GeneratorUtils;
import com.tsingxiao.unionj.generator.backend.springboot.docparser.entity.Backend;
import com.tsingxiao.unionj.generator.backend.springboot.docparser.entity.Vo;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator
 * @date:2020/11/22
 */
public class SpringbootFolderGenerator extends BackendGenerator {

  protected static final String OUTPUT_DIR = BackendGenerator.OUTPUT_DIR + File.separator + "springboot";

  private Backend backend;
  private String outputDir;
  private boolean zip;

  public static final class Builder {
    private Backend backend;
    private String outputDir = OUTPUT_DIR;
    private boolean zip = false;

    public Builder(Backend backend) {
      this.backend = backend;
    }

    public Builder outputDir(String outputDir) {
      this.outputDir = outputDir;
      return this;
    }

    public Builder zip(boolean zip) {
      this.zip = zip;
      return this;
    }

    public SpringbootFolderGenerator build() {
      SpringbootFolderGenerator backendFolderGenerator = new SpringbootFolderGenerator();
      backendFolderGenerator.backend = this.backend;
      backendFolderGenerator.outputDir = this.outputDir;
      backendFolderGenerator.zip = this.zip;
      return backendFolderGenerator;
    }

  }

  private SpringbootFolderGenerator() {
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
    for (Vo vo : backend.getVoList()) {
      VoJavaGenerator voJavaGenerator = new VoJavaGenerator(vo, this.outputDir + File.separator + "vo");
      voJavaGenerator.generate();
    }

    if (this.zip) {
      String outputFile = GeneratorUtils.getOutputDir("output") + File.separator + OUTPUT_DIR + ".zip";
      String sourceFile = getOutputFile();
      return GeneratorUtils.generateFolder(sourceFile, outputFile);
    }

    return getOutputFile();
  }
}
