package cloud.unionj.generator.frontend.vue;

import cloud.unionj.generator.GeneratorUtils;
import cloud.unionj.generator.mock.MockFolderGenerator;
import cloud.unionj.generator.mock.docparser.MockDocParser;
import cloud.unionj.generator.mock.docparser.entity.Api;
import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.service.ServiceFolderGenerator;
import cloud.unionj.generator.service.docparser.ServiceDocParser;
import cloud.unionj.generator.service.docparser.entity.BizServer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator
 * date 2020/11/22
 */
public class VueProjectGenerator extends VueGenerator {

  private String doc;
  private InputStream is;
  private Openapi3 openAPI;
  private String projectName;
  private String outputDir;
  private boolean scaffold;
  private boolean includeMock;

  public static final class Builder {
    private String doc;
    private InputStream is;
    private Openapi3 openAPI;
    private String projectName;
    private String outputDir = OUTPUT_DIR;
    private boolean scaffold;
    private boolean includeMock;

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

    public Builder is(InputStream is) {
      this.is = is;
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

    public Builder includeMock(boolean includeMock) {
      this.includeMock = includeMock;
      return this;
    }

    public VueProjectGenerator build() {
      VueProjectGenerator vueProjectGenerator = new VueProjectGenerator();
      vueProjectGenerator.projectName = this.projectName;
      vueProjectGenerator.outputDir = this.outputDir;
      vueProjectGenerator.doc = this.doc;
      vueProjectGenerator.is = this.is;
      vueProjectGenerator.openAPI = this.openAPI;
      vueProjectGenerator.scaffold = this.scaffold;
      vueProjectGenerator.includeMock = this.includeMock;
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
    if (StringUtils.isBlank(this.doc) && this.openAPI == null && this.is == null) {
      return null;
    }

    if (scaffold) {
      File folderZip = new File(getOutputFile() + ".zip");
      FileUtils.copyInputStreamToFile(ClassLoader.getSystemResourceAsStream(OUTPUT_DIR + ".zip"), folderZip);
      GeneratorUtils.unzip(folderZip.getAbsolutePath(), new File(GeneratorUtils.getOutputDir("")));
      folderZip.delete();
    }

    // generate README.md
    ReadmeMdGenerator readmeMdGenerator = new ReadmeMdGenerator(this.projectName);
    readmeMdGenerator.generate();

    // generate package.json
    PackageJsonGenerator packageJsonGenerator = new PackageJsonGenerator(this.projectName);
    packageJsonGenerator.generate();

    // generate mockServiceWorker.js
//    MockServiceWorkerJsGenerator mockServiceWorkerJsGenerator = new MockServiceWorkerJsGenerator();
//    mockServiceWorkerJsGenerator.generate();

    if (includeMock) {
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
    }

    BizServer bizServer;
    if (StringUtils.isNotBlank(this.doc)) {
      if (this.doc.startsWith("http")) {
        bizServer = ServiceDocParser.parse(new URL(this.doc));
      } else {
        bizServer = ServiceDocParser.parse(new File(this.doc));
      }
    } else if (this.is != null) {
      bizServer = ServiceDocParser.parse(this.is);
    } else {
      bizServer = ServiceDocParser.parse(this.openAPI);
    }
    if (bizServer != null) {
      ServiceFolderGenerator serviceFolderGenerator = new ServiceFolderGenerator.Builder(bizServer).outputDir(getOutputFile() + "/src/services").zip(false).build();
      serviceFolderGenerator.generate();

      if (StringUtils.isBlank(this.doc)) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        File oas3SpecFile = new File(getOutputFile() + "/src/services" + File.separator + "openapi3.json");
        FileUtils.writeStringToFile(oas3SpecFile, objectMapper.writeValueAsString(openAPI), StandardCharsets.UTF_8.name());
      }
    }

    String outputFile = GeneratorUtils.getOutputDir("output") + File.separator + this.projectName + "_vue.zip";
    String sourceFile = getOutputFile();
    return GeneratorUtils.generateFolder(sourceFile, outputFile);
  }
}
