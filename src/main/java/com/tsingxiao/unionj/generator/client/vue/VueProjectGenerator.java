package com.tsingxiao.unionj.generator.client.vue;

import com.tsingxiao.unionj.generator.GeneratorUtils;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.zip.ZipOutputStream;

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

    String zipFileName = GeneratorUtils.getOutputDir("output") + File.separator + this.projectName + "_vue.zip";
    String sourceFile = getOutputFile();
    FileOutputStream fos = new FileOutputStream(zipFileName);
    ZipOutputStream zipOut = new ZipOutputStream(fos);
    File fileToZip = new File(sourceFile);

    try {
      GeneratorUtils.zipFile(fileToZip, fileToZip.getName(), zipOut);
    } catch (Exception exception) {
      throw exception;
    } finally {
      zipOut.close();
      fos.close();
    }

    return zipFileName;
  }
}
