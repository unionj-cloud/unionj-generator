package cloud.unionj.generator.backend.springboot;

import cloud.unionj.generator.DefaultGenerator;
import cloud.unionj.generator.GeneratorUtils;
import cloud.unionj.generator.backend.docparser.entity.Proto;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static cloud.unionj.generator.backend.springboot.Constants.OUTPUT_DIR;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator
 * date 2020/11/22
 */
public class ControllerJavaGenerator extends DefaultGenerator {

  private Proto proto;
  private String outputDir;
  private String packageName;
  private String voPackageName;
  private String protoPackageName;
  private String servicePackageName;
  private String controllerName;
  private String serviceName;

  public ControllerJavaGenerator(Proto proto, String packageName, String outputDir, String voPackageName, String protoPackageName, String servicePackageName) {
    this.proto = proto;
    this.packageName = packageName;
    this.outputDir = outputDir + "/src/main/java/" + packageName.replace(".", "/");
    this.voPackageName = voPackageName;
    this.protoPackageName = protoPackageName;
    this.servicePackageName = servicePackageName;
    String baseName = StringUtils.removeEnd(StringUtils.capitalize(this.proto.getName()), "Proto");
    this.controllerName = baseName + "Controller";
    this.serviceName = baseName + "Service";
  }

  @Override
  public Map<String, Object> getInput() {
    Map<String, Object> input = new HashMap<>();
    input.put("packageName", this.packageName);
    input.put("voPackageName", this.voPackageName);
    input.put("protoPackageName", this.protoPackageName);
    input.put("servicePackageName", this.servicePackageName);
    input.put("imports", this.proto.getImports());
    input.put("name", this.controllerName);
    input.put("protoName", StringUtils.capitalize(this.proto.getName()));
    input.put("serviceName", this.serviceName);
    input.put("routers", this.proto.getRouters());
    return input;
  }

  @Override
  public String getTemplate() {
    return OUTPUT_DIR + "/controller.java.ftl";
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir) + File.separator + this.controllerName + ".java";
  }

}
