package com.tsingxiao.unionj.generator.frontend.vue;

import com.tsingxiao.unionj.generator.GeneratorUtils;
import com.tsingxiao.unionj.generator.mock.MockFolderGenerator;
import com.tsingxiao.unionj.generator.mock.docparser.MockDocParser;
import com.tsingxiao.unionj.generator.mock.docparser.entity.Api;
import com.tsingxiao.unionj.generator.openapi3.model.Openapi3;
import com.tsingxiao.unionj.generator.service.ServiceFolderGenerator;
import com.tsingxiao.unionj.generator.service.docparser.ServiceDocParser;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizServer;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator
 * @date:2020/11/22
 */
public class VueProjectGenerator extends VueGenerator {

  private String doc;
  private Openapi3 openAPI;
  private String projectName;
  private String outputDir;
  private boolean scaffold;

  public static final class Builder {
    private String doc;
    private Openapi3 openAPI;
    private String projectName;
    private String outputDir = OUTPUT_DIR;
    private boolean scaffold;

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

    public Builder openAPI(Openapi3 openAPI) {
      this.openAPI = openAPI;
      return this;
    }

    public Builder scaffold(boolean scaffold) {
      this.scaffold = scaffold;
      return this;
    }

    public VueProjectGenerator build() {
      VueProjectGenerator vueProjectGenerator = new VueProjectGenerator();
      vueProjectGenerator.projectName = this.projectName;
      vueProjectGenerator.outputDir = this.outputDir;
      vueProjectGenerator.doc = this.doc;
      vueProjectGenerator.openAPI = this.openAPI;
      vueProjectGenerator.scaffold = this.scaffold;
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
    if (StringUtils.isBlank(this.doc) && this.openAPI == null) {
      return null;
    }

    if (scaffold) {
      File folderZip = new File(getOutputFile() + ".zip");
      FileUtils.copyInputStreamToFile(ClassLoader.getSystemResourceAsStream(OUTPUT_DIR + ".zip"), folderZip);
      GeneratorUtils.unzip(folderZip.getAbsolutePath(), new File(GeneratorUtils.getOutputDir("")));
      folderZip.delete();
    }

    // generate README.md
    com.tsingxiao.unionj.generator.frontend.vue.ReadmeMdGenerator readmeMdGenerator = new ReadmeMdGenerator(this.projectName);
    readmeMdGenerator.generate();

    // generate package.json
    PackageJsonGenerator packageJsonGenerator = new PackageJsonGenerator(this.projectName);
    packageJsonGenerator.generate();

    // generate mockServiceWorker.js
//    MockServiceWorkerJsGenerator mockServiceWorkerJsGenerator = new MockServiceWorkerJsGenerator();
//    mockServiceWorkerJsGenerator.generate();

    Api api;
    if (StringUtils.isNotBlank(this.doc)) {
      if (this.doc.startsWith("http")) {
        api = MockDocParser.parse(new URL(this.doc));
      } else {
        api = MockDocParser.parse(new File(this.doc));
      }
    } else {
      api = MockDocParser.parse(this.openAPI);
    }

    if (api != null) {
      MockFolderGenerator mockFolderGenerator = new MockFolderGenerator.Builder(api).outputDir(getOutputFile() + "/src/mocks").zip(false).build();
      mockFolderGenerator.generate();
    }

    BizServer bizServer;
    if (StringUtils.isNotBlank(this.doc)) {
      if (this.doc.startsWith("http")) {
        bizServer = ServiceDocParser.parse(new URL(this.doc));
      } else {
        bizServer = ServiceDocParser.parse(new File(this.doc));
      }
    } else {
      bizServer = ServiceDocParser.parse(this.openAPI);
    }
    if (bizServer != null) {
      ServiceFolderGenerator serviceFolderGenerator = new ServiceFolderGenerator.Builder(bizServer).outputDir(getOutputFile() + "/src/services").zip(false).build();
      serviceFolderGenerator.generate();
    }

    String outputFile = GeneratorUtils.getOutputDir("output") + File.separator + this.projectName + "_vue.zip";
    String sourceFile = getOutputFile();
    return GeneratorUtils.generateFolder(sourceFile, outputFile);
  }
}
