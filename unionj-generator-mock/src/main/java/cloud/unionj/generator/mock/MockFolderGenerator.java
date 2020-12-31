package cloud.unionj.generator.mock;

import cloud.unionj.generator.GeneratorUtils;
import cloud.unionj.generator.mock.docparser.entity.Api;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator
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
    File folderZip = new File(getOutputFile() + ".zip");
    FileUtils.copyInputStreamToFile(ClassLoader.getSystemResourceAsStream(OUTPUT_DIR + ".zip"), folderZip);
    GeneratorUtils.unzip(folderZip.getAbsolutePath(), new File(getOutputFile()).getParentFile());
    folderZip.delete();

    HandlersJsGenerator handlersGenerator = new HandlersJsGenerator(this.api, this.outputDir);
    handlersGenerator.generate();

    if (this.zip) {
      String outputFile = GeneratorUtils.getOutputDir("output") + File.separator + OUTPUT_DIR + ".zip";
      String sourceFile = getOutputFile();
      return GeneratorUtils.generateFolder(sourceFile, outputFile);
    }

    return getOutputFile();
  }
}
