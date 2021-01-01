package cloud.unionj.generator.apidoc;

import cloud.unionj.generator.GeneratorUtils;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Map;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator
 * @date 2020/11/22
 */
public class ApiDocFolderGenerator extends ApiDocGenerator {

  private String doc;
  private String outputDir;
  private boolean zip;

  public static final class Builder {
    private String doc;
    private String outputDir = OUTPUT_DIR;
    private boolean zip = false;

    public Builder(String doc) {
      this.doc = doc;
    }

    public Builder outputDir(String outputDir) {
      this.outputDir = outputDir;
      return this;
    }

    public Builder zip(boolean zip) {
      this.zip = zip;
      return this;
    }

    public ApiDocFolderGenerator build() {
      ApiDocFolderGenerator apiDocFolderGenerator = new ApiDocFolderGenerator();
      apiDocFolderGenerator.doc = this.doc;
      apiDocFolderGenerator.outputDir = this.outputDir;
      apiDocFolderGenerator.zip = this.zip;
      return apiDocFolderGenerator;
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
    File folderZip = new File(getOutputFile() + ".zip");
    FileUtils.copyInputStreamToFile(ClassLoader.getSystemResourceAsStream(OUTPUT_DIR + ".zip"), folderZip);
    GeneratorUtils.unzip(folderZip.getAbsolutePath(), new File(GeneratorUtils.getOutputDir("")));
    folderZip.delete();

    // generate index.html.md
    IndexHtmlMdGenerator indexHtmlMdGenerator = new IndexHtmlMdGenerator(this.doc, this.outputDir);
    indexHtmlMdGenerator.generate();

    if (this.zip) {
      String outputFile = GeneratorUtils.getOutputDir("output") + File.separator + OUTPUT_DIR + ".zip";
      String sourceFile = getOutputFile();
      return GeneratorUtils.generateFolder(sourceFile, outputFile);
    }

    return getOutputFile();
  }
}
