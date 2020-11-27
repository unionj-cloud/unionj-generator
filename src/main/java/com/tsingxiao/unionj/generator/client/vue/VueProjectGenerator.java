package com.tsingxiao.unionj.generator.client.vue;

import com.tsingxiao.unionj.generator.GeneratorUtils;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator
 * @date:2020/11/22
 */
public class VueProjectGenerator extends VueGenerator {

  private String projectName;
  private String outputDir = OUTPUT_DIR;

  public VueProjectGenerator(String projectName) {
    this.projectName = projectName;
  }

  public VueProjectGenerator(String projectName, String outputDir) {
    this.projectName = projectName;
    this.outputDir = outputDir;
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
    File file = new File(VueProjectGenerator.class.getClassLoader().getResource(OUTPUT_DIR).getPath());
    FileUtils.copyDirectory(file, new File(getOutputFile()));

    // generate README.md
    ReadmeMdGenerator readmeMdGenerator = new ReadmeMdGenerator(this.projectName);
    readmeMdGenerator.generate();

    // generate package.json
    PackageJsonGenerator packageJsonGenerator = new PackageJsonGenerator(this.projectName);
    packageJsonGenerator.generate();

    String outputFile = GeneratorUtils.getOutputDir("output") + File.separator + this.projectName + "_vue.zip";
    String sourceFile = getOutputFile();
    return GeneratorUtils.generateFolder(sourceFile, outputFile);
  }
}
