package cloud.unionj.generator.backend.springboot;

import cloud.unionj.generator.DefaultGenerator;
import cloud.unionj.generator.GeneratorUtils;
import cloud.unionj.generator.backend.docparser.entity.Vo;
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
public class VoJavaGenerator extends DefaultGenerator {

  private Vo vo;
  private String outputDir;
  private String packageName;

  public VoJavaGenerator(Vo vo, String packageName, String outputDir, boolean noDefaultComment) {
    super(noDefaultComment);
    this.vo = vo;
    this.packageName = packageName;
    this.outputDir = outputDir + "/src/main/java/" + packageName.replace(".", "/");
  }

  public VoJavaGenerator(boolean noDefaultComment, String parentArtifactId, String companyName, String author,
      String createDate, String parentVersion, String year, String copyright, Vo vo, String outputDir,
      String packageName) {
    super(noDefaultComment, parentArtifactId, companyName, "", author, createDate, parentVersion, year, copyright);
    this.vo = vo;
    this.packageName = packageName;
    this.outputDir = outputDir + "/src/main/java/" + packageName.replace(".", "/");
  }

  @Override
  public Map<String, Object> getInput() {
    Map<String, Object> input = new HashMap<>();
    input.put("packageName", this.packageName);
    input.put("name", StringUtils.capitalize(this.vo.getName()));
    input.put("description", this.vo.getDescription());
    input.put("properties", this.vo.getProperties());
    input.put("enumTypes", this.vo.getEnumTypes());
    input.put("imports", this.vo.getImports());
    input.put("noDefaultComment", this.noDefaultComment);
    input.put("parentArtifactId", this.parentArtifactId);
    input.put("companyName", this.companyName);
    input.put("author", this.author);
    input.put("createDate", this.createDate);
    input.put("parentVersion", this.parentVersion);
    input.put("year", this.year);
    input.put("copyright", this.copyright);
    return input;
  }

  @Override
  public String getTemplate() {
    return OUTPUT_DIR + "/vo.java.ftl";
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir) + File.separator + StringUtils.capitalize(this.vo.getName())
                                                                                     .replaceAll("<.*>", "") + ".java";
  }

}
