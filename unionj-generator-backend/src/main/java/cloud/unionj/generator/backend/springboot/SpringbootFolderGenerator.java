package cloud.unionj.generator.backend.springboot;

import cloud.unionj.generator.GeneratorUtils;
import cloud.unionj.generator.backend.docparser.entity.Backend;
import cloud.unionj.generator.backend.docparser.entity.Proto;
import cloud.unionj.generator.backend.docparser.entity.Vo;

import java.io.File;

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
  private boolean pomProject;

  private OutputType outputType;

  private OutputConfig protoOutput;
  private OutputConfig voOutput;

  public static class OutputConfig {
    private String packageName;
    private String outputDir;

    public OutputConfig() {
    }

    public OutputConfig(String packageName, String outputDir) {
      this.packageName = packageName;
      this.outputDir = outputDir;
    }

    public String getPackageName() {
      return packageName;
    }

    public String getOutputDir() {
      return outputDir;
    }

    public void setPackageName(String packageName) {
      this.packageName = packageName;
    }

    public void setOutputDir(String outputDir) {
      this.outputDir = outputDir;
    }
  }

  public enum OutputType {
    /**
     * check
     */
    CHECK,

    /**
     * overwrite
     */
    OVERWRITE
  }

  public static final class Builder {
    private Backend backend;
    private String outputDir;

    private boolean zip = false;
    private boolean pomProject = false;

    private OutputType outputType;

    private OutputConfig protoOutput;
    private OutputConfig voOutput;

    public Builder(Backend backend) {
      this.backend = backend;
      this.outputDir = Constants.OUTPUT_DIR;
      this.outputType = OutputType.CHECK;

      this.protoOutput = new OutputConfig(Constants.PACKAGE_NAME + ".proto", outputDir + File.separator + "proto");
      this.voOutput = new OutputConfig(Constants.PACKAGE_NAME + ".vo", outputDir + File.separator + "vo");
    }

    public Builder zip(boolean zip) {
      this.zip = zip;
      return this;
    }

    public Builder pomProject(boolean pomProject) {
      this.pomProject = pomProject;
      return this;
    }

    public Builder outputType(OutputType outputType) {
      this.outputType = outputType;
      return this;
    }

    public Builder outputDir(String outputDir) {
      this.outputDir = outputDir;
      this.protoOutput.setOutputDir(outputDir + File.separator + "proto");
      this.voOutput.setOutputDir(outputDir + File.separator + "vo");
      return this;
    }


    public SpringbootFolderGenerator build() {
      SpringbootFolderGenerator backendFolderGenerator = new SpringbootFolderGenerator();
      backendFolderGenerator.backend = this.backend;
      backendFolderGenerator.outputDir = this.outputDir;

      backendFolderGenerator.zip = this.zip;
      backendFolderGenerator.pomProject = this.pomProject;
      backendFolderGenerator.outputType = this.outputType;

      backendFolderGenerator.protoOutput = this.protoOutput;
      backendFolderGenerator.voOutput = this.voOutput;

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
