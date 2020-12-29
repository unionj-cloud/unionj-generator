package com.tsingxiao.unionj.generator.backend.springboot;

import com.tsingxiao.unionj.generator.GeneratorUtils;
import com.tsingxiao.unionj.generator.backend.docparser.entity.Backend;
import com.tsingxiao.unionj.generator.backend.docparser.entity.Proto;
import com.tsingxiao.unionj.generator.backend.docparser.entity.Vo;
import lombok.SneakyThrows;

import java.io.File;

import static com.tsingxiao.unionj.generator.backend.springboot.Constants.OUTPUT_DIR;
import static com.tsingxiao.unionj.generator.backend.springboot.Constants.PACKAGE_NAME;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator
 * @date:2020/11/22
 */
public class SpringbootFolderGenerator {

  private Backend backend;
  private String outputDir;
  private boolean zip;
  protected String packageName;

  public static final class Builder {
    private Backend backend;
    private String outputDir = OUTPUT_DIR;
    private boolean zip = false;
    private String packageName = PACKAGE_NAME;

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

    public Builder packageName(String packageName) {
      this.packageName = packageName;
      return this;
    }

    public SpringbootFolderGenerator build() {
      SpringbootFolderGenerator backendFolderGenerator = new SpringbootFolderGenerator();
      backendFolderGenerator.backend = this.backend;
      backendFolderGenerator.outputDir = this.outputDir;
      backendFolderGenerator.zip = this.zip;
      backendFolderGenerator.packageName = this.packageName;
      return backendFolderGenerator;
    }

  }

  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir);
  }

  @SneakyThrows
  public String generate() {
    for (Vo vo : backend.getVoList()) {
      if (vo.isOutput()) {
        VoJavaGenerator voJavaGenerator = new VoJavaGenerator(vo, this.packageName);
        voJavaGenerator.generate();
      }
    }

    for (Proto proto : backend.getProtoList()) {
      ProtoJavaGenerator protoJavaGenerator = new ProtoJavaGenerator(proto, this.packageName);
      protoJavaGenerator.generate();
    }

    if (this.zip) {
      String outputFile = GeneratorUtils.getOutputDir("output") + File.separator + OUTPUT_DIR + ".zip";
      String sourceFile = getOutputFile();
      return GeneratorUtils.generateFolder(sourceFile, outputFile);
    }

    return getOutputFile();
  }
}
