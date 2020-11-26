package com.tsingxiao.unionj.generator.mock;

import com.tsingxiao.unionj.generator.mock.docparser.entity.Api;
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
public class MockFolderGenerator extends MockGenerator {

  private Api api;
  private String outputDir;
  private boolean zip;

  public static final class Builder {
    private Api api;
    private String outputDir = OUTPUT_DIR;
    private boolean zip = false;

    public Builder(Api api) {
      this.api = api;
    }

    public Builder outputDir(String outputDir) {
      this.outputDir = outputDir;
      return this;
    }

    public Builder zip(boolean zip) {
      this.zip = zip;
      return this;
    }

    public MockFolderGenerator build() {
      MockFolderGenerator mockFolderGenerator = new MockFolderGenerator();
      mockFolderGenerator.api = this.api;
      mockFolderGenerator.outputDir = this.outputDir;
      mockFolderGenerator.zip = this.zip;
      return mockFolderGenerator;
    }

  }

  private MockFolderGenerator() {
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
    File file = new File(MockFolderGenerator.class.getClassLoader().getResource(OUTPUT_DIR).getPath());
    FileUtils.copyDirectory(file, new File(getOutputFile()));

    HandlersJsGenerator handlersGenerator = new HandlersJsGenerator(this.api);
    handlersGenerator.generate();

    if (this.zip) {
      String zipFileName = GeneratorUtils.getOutputDir("output") + File.separator + OUTPUT_DIR + ".zip";
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

    return getOutputFile();
  }
}
