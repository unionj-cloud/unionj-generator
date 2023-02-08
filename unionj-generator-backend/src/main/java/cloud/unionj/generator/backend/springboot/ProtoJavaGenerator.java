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
public class ProtoJavaGenerator extends DefaultGenerator {

  private Proto proto;
  private String outputDir;
  private String packageName;
  private String voPackageName;

  public ProtoJavaGenerator(Proto proto, String packageName, String outputDir, String voPackageName,
      boolean noDefaultComment) {
    super(noDefaultComment);
    this.proto = proto;
    this.packageName = packageName;
    this.outputDir = outputDir + "/src/main/java/" + packageName.replace(".", "/");
    this.voPackageName = voPackageName;
  }

  public ProtoJavaGenerator(boolean noDefaultComment, String parentArtifactId, String companyName, String author,
      String createDate, String parentVersion, String year, String copyright, Proto proto, String outputDir,
      String packageName, String voPackageName) {
    super(noDefaultComment, parentArtifactId, companyName,
          StringUtils.removeEnd(StringUtils.capitalize(proto.getName()), "Proto"), author, createDate, parentVersion,
          year, copyright);
    this.proto = proto;
    this.packageName = packageName;
    this.outputDir = outputDir + "/src/main/java/" + packageName.replace(".", "/");
    this.voPackageName = voPackageName;
  }

  @Override
  public Map<String, Object> getInput() {
    Map<String, Object> input = new HashMap<>();
    input.put("packageName", this.packageName);
    input.put("base", this.proto.getBase());
    input.put("name", StringUtils.capitalize(this.proto.getName()));
    input.put("routers", this.proto.getRouters());
    input.put("imports", this.proto.getImports());
    input.put("voPackageName", this.voPackageName);
    input.put("noDefaultComment", this.noDefaultComment);
    input.put("parentArtifactId", this.parentArtifactId);
    input.put("companyName", this.companyName);
    input.put("baseName", this.baseName);
    input.put("author", this.author);
    input.put("createDate", this.createDate);
    input.put("parentVersion", this.parentVersion);
    input.put("year", this.year);
    input.put("copyright", this.copyright);
    return input;
  }

  @Override
  public String getTemplate() {
    return OUTPUT_DIR + "/proto.java.ftl";
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir) + File.separator + StringUtils.capitalize(
        this.proto.getName()) + ".java";
  }

}
