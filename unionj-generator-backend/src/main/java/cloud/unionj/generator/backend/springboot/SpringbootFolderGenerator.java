package cloud.unionj.generator.backend.springboot;

import cloud.unionj.generator.GeneratorUtils;
import cloud.unionj.generator.backend.docparser.entity.Backend;
import cloud.unionj.generator.backend.docparser.entity.Proto;
import cloud.unionj.generator.backend.docparser.entity.Vo;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator
 * date 2020/11/22
 */
public class SpringbootFolderGenerator {

  private Backend backend;
  private String packageName;
  private String outputDir;
  private boolean zip;
  private boolean pomProject;

  private OutputConfig protoOutput;
  private OutputConfig voOutput;

  public static class OutputConfig {
    private String packageName;
    private String outputDir;

    public static Builder builder() {
      return new Builder();
    }

    public static class Builder {

      private String packageName;
      private String outputDir;

      public Builder packageName(String packageName) {
        this.packageName = packageName;
        return this;
      }

      public Builder outputDir(String outputDir) {
        this.outputDir = outputDir;
        return this;
      }

      public OutputConfig build() {

        if (StringUtils.isBlank(this.packageName)) {
          throw new UnsupportedOperationException("package name required");
        }

        if (StringUtils.isBlank(this.outputDir)) {
          throw new UnsupportedOperationException("output dir required");
        }

        OutputConfig outputConfig = new OutputConfig();
        outputConfig.packageName = this.packageName;
        outputConfig.outputDir = this.outputDir;
        return outputConfig;
      }
    }

    private OutputConfig() {
    }

    public String getOutputDir() {
      return outputDir;
    }

    public String getPackageName() {
      return packageName;
    }
  }

  public static final class Builder {
    private Backend backend;
    private String packageName;
    private String outputDir;

    private boolean zip = false;
    private boolean pomProject = false;

    private OutputConfig.Builder protoOutputBuilder = OutputConfig.builder();
    private OutputConfig.Builder voOutputBuilder = OutputConfig.builder();

    public Builder(Backend backend) {
      this.backend = backend;
      this.packageName = Constants.PACKAGE_NAME;
      this.outputDir = Constants.OUTPUT_DIR;
    }

    public Builder zip(boolean zip) {
      this.zip = zip;
      return this;
    }

    public Builder pomProject(boolean pomProject) {
      this.pomProject = pomProject;
      return this;
    }

    public Builder packageName(String packageName) {
      this.protoOutputBuilder.packageName(packageName + ".proto");
      this.voOutputBuilder.packageName(packageName + ".vo");
      return this;
    }

    public Builder outputDir(String outputDir) {
      this.protoOutputBuilder.outputDir(outputDir + File.separator + "proto");
      this.voOutputBuilder.outputDir(outputDir + File.separator + "vo");
      return this;
    }

    public Builder protoPackageName(String protoPackageName) {
      this.protoOutputBuilder.packageName(protoPackageName);
      return this;
    }

    public Builder protoOutputDir(String protoOutputDir) {
      this.protoOutputBuilder.outputDir(protoOutputDir);
      return this;
    }

    public Builder voPackageName(String voPackageName) {
      this.voOutputBuilder.packageName(voPackageName);
      return this;
    }

    public Builder voOutputDir(String voOutputDir) {
      this.voOutputBuilder.outputDir(voOutputDir);
      return this;
    }

    public SpringbootFolderGenerator build() {
      SpringbootFolderGenerator backendFolderGenerator = new SpringbootFolderGenerator();
      backendFolderGenerator.backend = this.backend;
      backendFolderGenerator.zip = this.zip;
      backendFolderGenerator.pomProject = this.pomProject;

      backendFolderGenerator.outputDir = this.outputDir;
      backendFolderGenerator.packageName = this.packageName;

      backendFolderGenerator.protoOutput = this.protoOutputBuilder.build();
      backendFolderGenerator.voOutput = this.voOutputBuilder.build();

      return backendFolderGenerator;
    }

  }

  private SpringbootFolderGenerator() {
  }

  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir);
  }

  public void generate() {
    for (Vo vo : backend.getVoList()) {
      if (vo.isOutput()) {
        VoJavaGenerator voJavaGenerator = new VoJavaGenerator(vo,
            this.voOutput.getPackageName(), this.voOutput.getOutputDir());
        voJavaGenerator.generate();
      }
    }

    for (Proto proto : backend.getProtoList()) {
      ProtoJavaGenerator protoJavaGenerator = new ProtoJavaGenerator(proto,
          this.protoOutput.getPackageName(), this.protoOutput.getOutputDir(), this.voOutput.getPackageName());
      protoJavaGenerator.generate();
    }

    // TODO generate pom.xml

    if (this.zip) {
      String outputFile = this.outputDir + ".zip";
      String sourceFile = getOutputFile();
      GeneratorUtils.generateFolder(sourceFile, outputFile);
    }

  }
}
