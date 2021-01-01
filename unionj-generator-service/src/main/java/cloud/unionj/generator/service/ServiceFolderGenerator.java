package cloud.unionj.generator.service;

import cloud.unionj.generator.GeneratorUtils;
import cloud.unionj.generator.service.docparser.entity.BizServer;
import cloud.unionj.generator.service.docparser.entity.BizService;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Map;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator
 *  date 2020/11/22
 */
public class ServiceFolderGenerator extends ServiceGenerator {

  private BizServer bizServer;
  private String outputDir;
  private boolean zip;

  public static final class Builder {
    private BizServer bizServer;
    private String outputDir = OUTPUT_DIR;
    private boolean zip = false;

    public Builder(BizServer bizServer) {
      this.bizServer = bizServer;
    }

    public Builder outputDir(String outputDir) {
      this.outputDir = outputDir;
      return this;
    }

    public Builder zip(boolean zip) {
      this.zip = zip;
      return this;
    }

    public ServiceFolderGenerator build() {
      ServiceFolderGenerator serviceFolderGenerator = new ServiceFolderGenerator();
      serviceFolderGenerator.bizServer = this.bizServer;
      serviceFolderGenerator.outputDir = this.outputDir;
      serviceFolderGenerator.zip = this.zip;
      return serviceFolderGenerator;
    }

  }

  private ServiceFolderGenerator() {
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
    BizServiceTsGenerator bizServiceTsGenerator = new BizServiceTsGenerator(bizServer.getName(), this.outputDir);
    bizServiceTsGenerator.generate();

    TypesTsGenerator typesTsGenerator = new TypesTsGenerator(bizServer.getTypes(), this.outputDir);
    typesTsGenerator.generate();

    for (BizService bizService : bizServer.getServices()) {
      ServiceTsGenerator serviceTsGenerator = new ServiceTsGenerator(bizService, this.outputDir);
      serviceTsGenerator.generate();
    }

    if (this.zip) {
      String outputFile = GeneratorUtils.getOutputDir("output") + File.separator + OUTPUT_DIR + ".zip";
      String sourceFile = getOutputFile();
      return GeneratorUtils.generateFolder(sourceFile, outputFile);
    }

    return getOutputFile();
  }
}
