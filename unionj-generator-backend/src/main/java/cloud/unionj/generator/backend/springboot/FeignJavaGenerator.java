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
public class FeignJavaGenerator extends DefaultGenerator {

  private Proto proto;
  private String outputDir;
  private String packageName;
  private String voPackageName;
  private String serviceId;
  private String serviceBaseUrlKey;
  private String name;

  public FeignJavaGenerator(Proto proto, String packageName, String outputDir, String voPackageName, String serviceId
      , String serviceBaseUrlKey) {
    this.proto = proto;
    this.packageName = packageName;
    this.outputDir = outputDir + "/src/main/java/" + packageName.replace(".", "/");
    this.voPackageName = voPackageName;
    this.serviceId = serviceId;
    this.serviceBaseUrlKey = serviceBaseUrlKey;
    this.name = StringUtils.removeEnd(StringUtils.capitalize(this.proto.getName()), "Proto") + "Client";
  }

  public FeignJavaGenerator(Proto proto, String packageName, String outputDir, String voPackageName, String serviceId) {
    this.proto = proto;
    this.packageName = packageName;
    this.outputDir = outputDir + "/src/main/java/" + packageName.replace(".", "/");
    this.voPackageName = voPackageName;
    this.serviceId = serviceId;
    this.name = StringUtils.removeEnd(StringUtils.capitalize(this.proto.getName()), "Proto") + "Client";
  }

  @Override
  public Map<String, Object> getInput() {
    Map<String, Object> input = new HashMap<>();
    input.put("packageName", this.packageName);
    input.put("base", this.proto.getBase());
    input.put("name", this.name);
    input.put("routers", this.proto.getRouters());
    input.put("imports", this.proto.getImports());
    input.put("voPackageName", this.voPackageName);
    input.put("serviceId", this.serviceId);
    input.put("serviceBaseUrlKey", this.serviceBaseUrlKey);
    return input;
  }

  @Override
  public String getTemplate() {
    return OUTPUT_DIR + "/feign.java.ftl";
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir) + File.separator + this.name + ".java";
  }

}
