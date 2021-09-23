package cloud.unionj.generator.backend.springboot;

import cloud.unionj.generator.DefaultGenerator;
import cloud.unionj.generator.GeneratorUtils;
import cloud.unionj.generator.backend.docparser.entity.Proto;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ServiceJavaGenerator extends DefaultGenerator {

  private Proto proto;
  private String outputDir;
  private String packageName;
  private String protoPackageName;
  private String serviceName;

  public ServiceJavaGenerator(Proto proto, String packageName, String outputDir, String protoPackageName) {
    this.proto = proto;
    this.packageName = packageName;
    this.outputDir = outputDir + "/src/main/java/" + packageName.replace(".", "/");
    this.protoPackageName = protoPackageName;
    String baseName = StringUtils.removeEnd(StringUtils.capitalize(this.proto.getName()), "Proto");
    this.serviceName = baseName + "Service";
  }

  @Override
  public Map<String, Object> getInput() {
    Map<String, Object> input = new HashMap<>();
    input.put("packageName", this.packageName);
    input.put("name", this.serviceName);
    input.put("protoName", StringUtils.capitalize(this.proto.getName()));
    input.put("protoPackageName", this.protoPackageName);
    return input;
  }

  @Override
  public String getTemplate() {
    return OUTPUT_DIR + "/service.java.ftl";
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir) + File.separator + this.serviceName + ".java";
  }

  @Override
  public String generate() {
    File file = new File(getOutputFile());
    if (file.exists()) {
      log.info(file + " already exists, skip generating");
      return getOutputFile();
    }
    return super.generate();
  }
}
