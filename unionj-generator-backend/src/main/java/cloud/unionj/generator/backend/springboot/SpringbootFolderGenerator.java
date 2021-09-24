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

/**
 * Proto package, controller package and vo package will be replaced completely.
 * <p>
 * Existing files in service package will be skipped and not be changed, only new files will be generated.
 */
public class SpringbootFolderGenerator {

  private Backend backend;
  private String outputDir;

  private boolean zip;
  private boolean pomProject;

  private OutputType protoOutputType;
  private OutputType voOutputType;
  private OutputType controllerOutputType;
  private OutputType serviceOutputType;

  private OutputConfig protoOutput;
  private OutputConfig voOutput;
  private OutputConfig controllerOutput;
  private OutputConfig serviceOutput;

  private ProtoPomGenerator protoPomGenerator;
  private VoPomGenerator voPomGenerator;
  private ControllerPomGenerator controllerPomGenerator;
  private ServicePomGenerator servicePomGenerator;

  private Mode mode;

  public static final class Builder {
    private Backend backend;
    private String outputDir;

    private boolean zip = false;
    private boolean pomProject = false;

    private OutputType protoOutputType;
    private OutputType voOutputType;
    private OutputType controllerOutputType;
    private OutputType serviceOutputType;

    private OutputConfig protoOutput;
    private OutputConfig voOutput;
    private OutputConfig controllerOutput;
    private OutputConfig serviceOutput;

    private ProtoPomGenerator protoPomGenerator;
    private VoPomGenerator voPomGenerator;
    private ControllerPomGenerator controllerPomGenerator;
    private ServicePomGenerator servicePomGenerator;

    private Mode mode;

    public Builder(Backend backend) {
      this.backend = backend;
      this.outputDir = OUTPUT_DIR;
      this.protoOutputType = OutputType.OVERWRITE;
      this.voOutputType = OutputType.OVERWRITE;
      this.controllerOutputType = OutputType.OVERWRITE;
      this.serviceOutputType = OutputType.CHECK;

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
      this.servicePomGenerator = new ServicePomGenerator();

      this.mode = Mode.FULL;
    }

    public Builder zip(boolean zip) {
      this.zip = zip;
      return this;
    }

    public Builder mode(Mode mode) {
      this.mode = mode;
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

      this.servicePomGenerator.parentGroupId(parentGroupId);
      this.servicePomGenerator.groupIdAsParent();
      this.servicePomGenerator.voGroupIdAsParent();
      this.servicePomGenerator.protoGroupIdAsParent();
      return this;
    }

    public Builder pomParentArtifactId(String parentArtifactId) {
      this.protoPomGenerator.parentArtifactId(parentArtifactId);
      this.voPomGenerator.parentArtifactId(parentArtifactId);
      this.controllerPomGenerator.parentArtifactId(parentArtifactId);
      this.servicePomGenerator.parentArtifactId(parentArtifactId);

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

      this.servicePomGenerator.parentVersion(parentVersion);
      this.servicePomGenerator.versionAsParent();
      this.servicePomGenerator.voVersionAsParent();
      this.servicePomGenerator.protoVersionAsParent();
      return this;
    }

    public Builder pomProtoArtifactId(String protoArtifactId) {
      this.protoPomGenerator.artifactId(protoArtifactId);
      this.servicePomGenerator.protoArtifactId(protoArtifactId);
      this.controllerPomGenerator.protoArtifactId(protoArtifactId);
      return this;
    }

    public Builder pomServiceArtifactId(String serviceArtifactId) {
      this.servicePomGenerator.artifactId(serviceArtifactId);
      this.controllerPomGenerator.serviceArtifactId(serviceArtifactId);
      return this;
    }

    public Builder pomControllerArtifactId(String controllerArtifactId) {
      this.controllerPomGenerator.artifactId(controllerArtifactId);
      return this;
    }

    public Builder pomVoArtifactId(String voArtifactId) {
      this.voPomGenerator.artifactId(voArtifactId);
      this.protoPomGenerator.voArtifactId(voArtifactId);
      this.servicePomGenerator.voArtifactId(voArtifactId);
      return this;
    }

    public Builder pomProtoOutputDir(String pomProtoOutputDir) {
      this.protoPomGenerator.outputDir(pomProtoOutputDir);
      return this;
    }

    public Builder pomServiceOutputDir(String pomServiceOutputDir) {
      this.servicePomGenerator.outputDir(pomServiceOutputDir);
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
      this.servicePomGenerator = new ServicePomGenerator(serviceOutput.getOutputDir());
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

    public Builder servicePomGenerator(ServicePomGenerator servicePomGenerator) {
      this.servicePomGenerator = servicePomGenerator;
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
      backendFolderGenerator.protoOutputType = this.protoOutputType;
      backendFolderGenerator.voOutputType = this.voOutputType;
      backendFolderGenerator.controllerOutputType = this.controllerOutputType;
      backendFolderGenerator.serviceOutputType = this.serviceOutputType;

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
      backendFolderGenerator.servicePomGenerator = this.servicePomGenerator;

      backendFolderGenerator.mode = this.mode;

      return backendFolderGenerator;
    }

  }

  private SpringbootFolderGenerator() {
  }

  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir);
  }

  public void generate() throws IOException {
    FileUtils.deleteDirectory(new File(this.voOutput.getOutputDir() + "/src"));
    FileUtils.deleteDirectory(new File(this.protoOutput.getOutputDir() + "/src"));
    if (this.mode == Mode.FULL) {
      FileUtils.deleteDirectory(new File(this.controllerOutput.getOutputDir() + "/src"));
    }

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

      if (this.mode == Mode.FULL) {
        ControllerJavaGenerator controllerJavaGenerator = new ControllerJavaGenerator(proto,
            this.controllerOutput.getPackageName(), this.controllerOutput.getOutputDir(),
            this.voOutput.getPackageName(), this.protoOutput.getPackageName(), this.serviceOutput.getPackageName());
        controllerJavaGenerator.generate();

        ServiceJavaGenerator serviceJavaGenerator = new ServiceJavaGenerator(proto,
            this.serviceOutput.getPackageName(), this.serviceOutput.getOutputDir(), this.protoOutput.getPackageName());
        serviceJavaGenerator.generate();

        ServiceImplJavaGenerator serviceImplJavaGenerator = new ServiceImplJavaGenerator(proto,
            this.serviceOutput.getPackageName(), this.serviceOutput.getOutputDir(), this.voOutput.getPackageName());
        serviceImplJavaGenerator.generate();
      }
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
      if (this.mode == Mode.FULL) {
        pom = new File(this.controllerPomGenerator.outputDir + "/pom.xml");
        if (!pom.exists()) {
          this.controllerPomGenerator.generate();
        }
        pom = new File(this.servicePomGenerator.outputDir + "/pom.xml");
        if (!pom.exists()) {
          this.servicePomGenerator.generate();
        }
      }
    }

    if (this.zip) {
      // TODO gen zip file
      String outputFile = this.outputDir + ".zip";
      String sourceFile = getOutputFile();
      GeneratorUtils.generateFolder(sourceFile, outputFile);
    }

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
