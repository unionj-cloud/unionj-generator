package cloud.unionj.generator.backend.springboot;

import cloud.unionj.generator.GeneratorUtils;
import cloud.unionj.generator.backend.docparser.entity.Backend;
import cloud.unionj.generator.backend.docparser.entity.Proto;
import cloud.unionj.generator.backend.docparser.entity.Vo;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static cloud.unionj.generator.backend.springboot.Constants.*;

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
  private OutputConfig controllerOutput;
  private OutputConfig serviceOutput;

  private ProtoPomGenerator protoPomGenerator;
  private VoPomGenerator voPomGenerator;
  private ControllerPomGenerator controllerPomGenerator;

  public static final class Builder {
    private Backend backend;
    private String outputDir;

    private boolean zip = false;
    private boolean pomProject = false;

    private OutputType outputType;

    private OutputConfig protoOutput;
    private OutputConfig voOutput;
    private OutputConfig controllerOutput;
    private OutputConfig serviceOutput;

    private ProtoPomGenerator protoPomGenerator;
    private VoPomGenerator voPomGenerator;
    private ControllerPomGenerator controllerPomGenerator;

    public Builder(Backend backend) {
      this.backend = backend;
      this.outputDir = OUTPUT_DIR;
      this.outputType = OutputType.CHECK;

      this.protoOutput = new OutputConfig(
          PACKAGE_NAME + DOT + DEFAULT_PROTO_PACKAGE,
          OUTPUT_DIR + File.separator + DEFAULT_PROTO_PACKAGE);
      this.voOutput = new OutputConfig(
          PACKAGE_NAME + DOT + DEFAULT_VO_PACKAGE,
          OUTPUT_DIR + File.separator + DEFAULT_VO_PACKAGE);
      this.controllerOutput = new OutputConfig(
          PACKAGE_NAME + DOT + DEFAULT_CONTROLLER_PACKAGE,
          OUTPUT_DIR + File.separator + DEFAULT_CONTROLLER_PACKAGE);
      this.serviceOutput = new OutputConfig(
          PACKAGE_NAME + DOT + DEFAULT_SERVICE_PACKAGE,
          OUTPUT_DIR + File.separator + DEFAULT_SERVICE_PACKAGE);

      this.protoPomGenerator = new ProtoPomGenerator();
      this.voPomGenerator = new VoPomGenerator();
      this.controllerPomGenerator = new ControllerPomGenerator();
    }

    public Builder zip(boolean zip) {
      this.zip = zip;
      return this;
    }

    public Builder pomProject(boolean pomProject) {
      this.pomProject = pomProject;
      return this;
    }

    public Builder pomParentGroupId(String parentGroupId) {
      this.protoPomGenerator.parentGroupId(parentGroupId);
      this.protoPomGenerator.groupIdAsParent();
      this.protoPomGenerator.voGroupIdAsParent();

      this.voPomGenerator.parentGroupId(parentGroupId);
      this.voPomGenerator.groupIdAsParent();

      this.controllerPomGenerator.parentGroupId(parentGroupId);
      this.controllerPomGenerator.groupIdAsParent();
      this.controllerPomGenerator.protoGroupIdAsParent();
      this.controllerPomGenerator.serviceGroupIdAsParent();
      return this;
    }

    public Builder pomParentArtifactId(String parentArtifactId) {
      this.protoPomGenerator.parentArtifactId(parentArtifactId);
      this.voPomGenerator.parentArtifactId(parentArtifactId);
      this.controllerPomGenerator.parentArtifactId(parentArtifactId);

      return this;
    }

    public Builder pomParentVersion(String parentVersion) {
      this.protoPomGenerator.parentVersion(parentVersion);
      this.protoPomGenerator.versionAsParent();
      this.protoPomGenerator.voVersionAsParent();

      this.voPomGenerator.parentVersion(parentVersion);
      this.voPomGenerator.versionAsParent();

      this.controllerPomGenerator.parentVersion(parentVersion);
      this.controllerPomGenerator.versionAsParent();
      this.controllerPomGenerator.protoVersionAsParent();
      this.controllerPomGenerator.serviceVersionAsParent();
      return this;
    }

    public Builder pomProtoArtifactId(String protoArtifactId) {
      this.protoPomGenerator.artifactId(protoArtifactId);
      return this;
    }

    public Builder pomControllerArtifactId(String controllerArtifactId) {
      this.controllerPomGenerator.artifactId(controllerArtifactId);
      return this;
    }

    public Builder pomVoArtifactId(String voArtifactId) {
      this.voPomGenerator.artifactId(voArtifactId);
      this.protoPomGenerator.voArtifactId(voArtifactId);
      return this;
    }

    public Builder pomProtoOutputDir(String pomProtoOutputDir) {
      this.protoPomGenerator.outputDir(pomProtoOutputDir);
      return this;
    }

    public Builder pomControllerOutputDir(String pomControllerOutputDir) {
      this.controllerPomGenerator.outputDir(pomControllerOutputDir);
      return this;
    }

    public Builder pomVoOutputDir(String pomVoOutputDir) {
      this.voPomGenerator.outputDir(pomVoOutputDir);
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
      this.controllerOutput.setOutputDir(outputDir + File.separator + DEFAULT_CONTROLLER_PACKAGE);
      this.serviceOutput.setOutputDir(outputDir + File.separator + DEFAULT_SERVICE_PACKAGE);
      return this;
    }

    public Builder packageName(String packageName) {
      this.protoOutput.setPackageName(packageName + DOT + DEFAULT_PROTO_PACKAGE);
      this.voOutput.setPackageName(packageName + DOT + DEFAULT_VO_PACKAGE);
      this.controllerOutput.setPackageName(packageName + DOT + DEFAULT_CONTROLLER_PACKAGE);
      this.serviceOutput.setPackageName(packageName + DOT + DEFAULT_SERVICE_PACKAGE);
      return this;
    }

    public Builder protoOutput(OutputConfig protoOutput) {
      this.protoOutput = protoOutput;
      this.protoPomGenerator = new ProtoPomGenerator(protoOutput.getOutputDir());
      return this;
    }

    public Builder controllerOutput(OutputConfig controllerOutput) {
      this.controllerOutput = controllerOutput;
      this.controllerPomGenerator = new ControllerPomGenerator(controllerOutput.getOutputDir());
      return this;
    }

    public Builder serviceOutput(OutputConfig serviceOutput) {
      this.serviceOutput = serviceOutput;
      // TODO
//      this.controllerPomGenerator = new ControllerPomGenerator(serviceOutput.getOutputDir());
      return this;
    }

    public Builder voOutput(OutputConfig voOutput) {
      this.voOutput = voOutput;
      this.voPomGenerator = new VoPomGenerator(voOutput.getOutputDir());
      return this;
    }

    public Builder protoPomGenerator(ProtoPomGenerator protoPomGenerator) {
      this.protoPomGenerator = protoPomGenerator;
      return this;
    }

    public Builder controllerPomGenerator(ControllerPomGenerator controllerPomGenerator) {
      this.controllerPomGenerator = controllerPomGenerator;
      return this;
    }

    public Builder voPomGenerator(VoPomGenerator voPomGenerator) {
      this.voPomGenerator = voPomGenerator;
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
      this.controllerOutput.validate();
      this.serviceOutput.validate();

      backendFolderGenerator.protoOutput = this.protoOutput;
      backendFolderGenerator.voOutput = this.voOutput;
      backendFolderGenerator.controllerOutput = this.controllerOutput;
      backendFolderGenerator.serviceOutput = this.serviceOutput;

      backendFolderGenerator.protoPomGenerator = this.protoPomGenerator;
      backendFolderGenerator.voPomGenerator = this.voPomGenerator;
      backendFolderGenerator.controllerPomGenerator = this.controllerPomGenerator;
      // TODO servicePomGenerator

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

      ControllerJavaGenerator controllerJavaGenerator = new ControllerJavaGenerator(proto,
          this.controllerOutput.getPackageName(), this.controllerOutput.getOutputDir(),
          this.voOutput.getPackageName(), this.protoOutput.getPackageName(), this.serviceOutput.getPackageName());
      controllerJavaGenerator.generate();
    }

    if (pomProject) {
      File pom = new File(this.protoPomGenerator.outputDir + "/pom.xml");
      if (!pom.exists()) {
        this.protoPomGenerator.generate();
      }
      pom = new File(this.voPomGenerator.outputDir + "/pom.xml");
      if (!pom.exists()) {
        this.voPomGenerator.generate();
      }
      pom = new File(this.controllerPomGenerator.outputDir + "/pom.xml");
      if (!pom.exists()) {
        this.controllerPomGenerator.generate();
      }
      // TODO
    }

    if (this.zip) {
      // TODO gen zip file
      String outputFile = this.outputDir + ".zip";
      String sourceFile = getOutputFile();
      GeneratorUtils.generateFolder(sourceFile, outputFile);
    }

  }

  private void checkOutput() throws IOException {
    String voOutputDir = this.voOutput.getOutputDir();
    String protoOutputDir = this.protoOutput.getOutputDir();
    String controllerOutputDir = this.controllerOutput.getOutputDir();
    String serviceOutputDir = this.serviceOutput.getOutputDir();

    if (OutputType.CHECK.equals(this.outputType)) {

      if (!dirEmpty(voOutputDir)) {
        throw new UnsupportedOperationException(voOutputDir + " is not empty");
      }

      if (!dirEmpty(protoOutputDir)) {
        throw new UnsupportedOperationException(protoOutputDir + " is not empty");
      }

      if (!dirEmpty(controllerOutputDir)) {
        throw new UnsupportedOperationException(controllerOutputDir + " is not empty");
      }

      if (!dirEmpty(serviceOutputDir)) {
        throw new UnsupportedOperationException(serviceOutputDir + " is not empty");
      }
    }

    FileUtils.deleteDirectory(new File(voOutputDir + "/src"));
    FileUtils.deleteDirectory(new File(protoOutputDir + "/src"));
    FileUtils.deleteDirectory(new File(controllerOutputDir + "/src"));
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
