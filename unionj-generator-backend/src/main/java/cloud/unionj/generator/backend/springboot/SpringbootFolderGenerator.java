package cloud.unionj.generator.backend.springboot;

import cloud.unionj.generator.GeneratorUtils;
import cloud.unionj.generator.backend.docparser.entity.Backend;
import cloud.unionj.generator.backend.docparser.entity.Proto;
import cloud.unionj.generator.backend.docparser.entity.Vo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

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

    public OutputConfig(String packageName, String outputDir) {
      this.packageName = packageName;
      this.outputDir = outputDir;

      validate();
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

    public void validate() throws UnsupportedOperationException {
      if (StringUtils.isBlank(packageName)) {
        throw new UnsupportedOperationException("packageName required");
      }

      if (StringUtils.isBlank(outputDir)) {
        throw new UnsupportedOperationException("outputDir required");
      }
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

    private static final String DEFAULT_PROTO_PACKAGE = "proto";
    private static final String DEFAULT_VO_PACKAGE = "vo";
    private static final String DOT = ".";

    public Builder(Backend backend) {
      this.backend = backend;
      this.outputDir = Constants.OUTPUT_DIR;
      this.outputType = OutputType.CHECK;

      this.protoOutput = new OutputConfig(
          Constants.PACKAGE_NAME + DOT + DEFAULT_PROTO_PACKAGE,
          Constants.OUTPUT_DIR + File.separator + DEFAULT_PROTO_PACKAGE);
      this.voOutput = new OutputConfig(
          Constants.PACKAGE_NAME + DOT + DEFAULT_VO_PACKAGE,
          Constants.OUTPUT_DIR + File.separator + DEFAULT_VO_PACKAGE);
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
      this.protoOutput.setOutputDir(outputDir + File.separator + DEFAULT_PROTO_PACKAGE);
      this.voOutput.setOutputDir(outputDir + File.separator + DEFAULT_VO_PACKAGE);
      return this;
    }

    public Builder packageName(String packageName) {
      this.protoOutput.setPackageName(packageName + DOT + DEFAULT_PROTO_PACKAGE);
      this.voOutput.setPackageName(packageName + DOT + DEFAULT_VO_PACKAGE);
      return this;
    }

    public Builder protoOutput(OutputConfig protoOutput) {
      this.protoOutput = protoOutput;
      return this;
    }

    public Builder voOutput(OutputConfig voOutput) {
      this.voOutput = voOutput;
      return this;
    }


    public SpringbootFolderGenerator build() {
      SpringbootFolderGenerator backendFolderGenerator = new SpringbootFolderGenerator();
      backendFolderGenerator.backend = this.backend;
      backendFolderGenerator.outputDir = this.outputDir;

      backendFolderGenerator.zip = this.zip;
      backendFolderGenerator.pomProject = this.pomProject;
      backendFolderGenerator.outputType = this.outputType;


      this.protoOutput.validate();
      this.voOutput.validate();

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

  public void generate() throws IOException {

    // 检查覆盖类型
    checkOutput();

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
      // TODO
      String outputFile = this.outputDir + ".zip";
      String sourceFile = getOutputFile();
      GeneratorUtils.generateFolder(sourceFile, outputFile);
    }

  }

  private void checkOutput() throws IOException {
    String voOutputDir = this.voOutput.getOutputDir();
    String protoOutputDir = this.protoOutput.getOutputDir();

    if (OutputType.CHECK.equals(this.outputType)) {

      if (!dirEmpty(voOutputDir)) {
        throw new UnsupportedOperationException(voOutputDir + " is not empty");
      }

      if (!dirEmpty(protoOutputDir)) {
        throw new UnsupportedOperationException(protoOutputDir + " is not empty");
      }
    }

    FileUtils.deleteDirectory(new File(voOutputDir));
    FileUtils.deleteDirectory(new File(protoOutputDir));
  }

  private boolean dirEmpty(String dirPath) {
    File dir = new File(dirPath);
    if (dir.exists() && !dir.isDirectory()) {
      throw new UnsupportedOperationException("not dir: " + dirPath);
    }

    String[] list = dir.list();
    if (list == null) {
      return true;
    }

    return list.length <= 0;
  }

}
