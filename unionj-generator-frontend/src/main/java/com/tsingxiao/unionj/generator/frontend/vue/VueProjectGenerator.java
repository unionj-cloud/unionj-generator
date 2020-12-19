package com.tsingxiao.unionj.generator.frontend.vue;

import com.tsingxiao.unionj.generator.GeneratorUtils;
import com.tsingxiao.unionj.generator.apidoc.ApiDocFolderGenerator;
import com.tsingxiao.unionj.generator.mock.MockFolderGenerator;
import com.tsingxiao.unionj.generator.mock.docparser.MockDocParser;
import com.tsingxiao.unionj.generator.mock.docparser.entity.Api;
import com.tsingxiao.unionj.generator.service.ServiceFolderGenerator;
import com.tsingxiao.unionj.generator.service.docparser.ServiceDocParser;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizServer;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator
 * @date:2020/11/22
 */
public class VueProjectGenerator extends VueGenerator {

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

    public VueProjectGenerator build() {
      VueProjectGenerator vueProjectGenerator = new VueProjectGenerator();
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
    File file = new File(VueProjectGenerator.class.getClassLoader().getResource(OUTPUT_DIR).getPath());
    File dest = new File(getOutputFile());
    FileUtils.copyDirectory(file, dest);

    // generate README.md
    com.tsingxiao.unionj.generator.frontend.vue.ReadmeMdGenerator readmeMdGenerator = new ReadmeMdGenerator(this.projectName);
    readmeMdGenerator.generate();

    // generate package.json
    PackageJsonGenerator packageJsonGenerator = new PackageJsonGenerator(this.projectName);
    packageJsonGenerator.generate();

    // generate mockServiceWorker.js
//    MockServiceWorkerJsGenerator mockServiceWorkerJsGenerator = new MockServiceWorkerJsGenerator();
//    mockServiceWorkerJsGenerator.generate();

    if (StringUtils.isNotBlank(this.doc)) {
      MockDocParser mockDocParser = new MockDocParser(this.doc);
      Api api = mockDocParser.parse();
      MockFolderGenerator mockFolderGenerator = new MockFolderGenerator.Builder(api).outputDir(getOutputFile() + "/src/mocks").zip(false).build();
      mockFolderGenerator.generate();

      ServiceDocParser serviceDocParser = new ServiceDocParser(this.doc);
      BizServer bizServer = serviceDocParser.parse();
      ServiceFolderGenerator serviceFolderGenerator = new ServiceFolderGenerator.Builder(bizServer).outputDir(getOutputFile() + "/src/services").zip(false).build();
      serviceFolderGenerator.generate();

      ApiDocFolderGenerator apiDocFolderGenerator = new ApiDocFolderGenerator.Builder(this.doc).outputDir(getOutputFile() + "/apidoc").zip(false).build();
      apiDocFolderGenerator.generate();
    }

    String outputFile = GeneratorUtils.getOutputDir("output") + File.separator + this.projectName + "_vue.zip";
    String sourceFile = getOutputFile();
    return GeneratorUtils.generateFolder(sourceFile, outputFile);
  }
}
