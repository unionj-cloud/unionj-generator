package cloud.unionj.generator.backend.springboot;

import cloud.unionj.generator.GeneratorUtils;
import cloud.unionj.generator.backend.docparser.entity.Backend;
import cloud.unionj.generator.backend.docparser.entity.Proto;
import cloud.unionj.generator.backend.docparser.entity.Vo;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator
 * date 2020/11/22
 */
public class SpringbootFolderGenerator {

  private Backend backend;
  private String outputDir;
  private boolean zip;
  protected String packageName;

  private String protoPackageName;
  private String voPackageName;

  private String protoOutputDir;
  private String voOutputDir;

  public static final class Builder {
    private Backend backend;
    private String outputDir = Constants.OUTPUT_DIR;
    private boolean zip = false;
    private String packageName = Constants.PACKAGE_NAME;

    private String protoPackageName;
    private String voPackageName;

    private String protoOutputDir;
    private String voOutputDir;

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

    public Builder protoPackageName(String protoPackageName) {
      this.protoPackageName = protoPackageName;
      return this;
    }

    public Builder voPackageName(String voPackageName) {
      this.voPackageName = voPackageName;
      return this;
    }

    public Builder protoOutputDir(String protoOutputDir) {
      this.protoOutputDir = protoOutputDir;
      return this;
    }

    public Builder voOutputDir(String voOutputDir) {
      this.voOutputDir = voOutputDir;
      return this;
    }

    public SpringbootFolderGenerator build() {
      SpringbootFolderGenerator backendFolderGenerator = new SpringbootFolderGenerator();
      backendFolderGenerator.backend = this.backend;
      backendFolderGenerator.zip = this.zip;

      backendFolderGenerator.outputDir = this.outputDir;
      backendFolderGenerator.packageName = this.packageName;

      if (StringUtils.isBlank(this.protoPackageName)) {
        this.protoPackageName = this.packageName + ".proto";
      }
      backendFolderGenerator.protoPackageName = this.protoPackageName;

      if (StringUtils.isBlank(this.voPackageName)) {
        this.voPackageName = this.voPackageName + ".vo";
      }
      backendFolderGenerator.voPackageName = this.voPackageName;
      backendFolderGenerator.protoOutputDir = this.protoOutputDir;
      backendFolderGenerator.voOutputDir = this.voOutputDir;
      return backendFolderGenerator;
    }

  }

  protected SpringbootFolderGenerator() {

  }

  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir);
  }

  @SneakyThrows
  public String generate() {
    for (Vo vo : backend.getVoList()) {
      if (vo.isOutput()) {
        VoJavaGenerator voJavaGenerator = new VoJavaGenerator(vo, this.voPackageName, this.voOutputDir);
        voJavaGenerator.generate();
      }
    }

    for (Proto proto : backend.getProtoList()) {
      ProtoJavaGenerator protoJavaGenerator = new ProtoJavaGenerator(proto, this.protoPackageName,
          this.protoOutputDir, this.voPackageName);
      protoJavaGenerator.generate();
    }

    if (this.zip) {
      String outputFile = this.outputDir + ".zip";
      String sourceFile = getOutputFile();
      return GeneratorUtils.generateFolder(sourceFile, outputFile);
    }

    return getOutputFile();
  }
}
