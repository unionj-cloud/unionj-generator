package cloud.unionj.generator.mybatis;

import cloud.unionj.generator.DefaultGenerator;
import cloud.unionj.generator.GeneratorUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

import static cloud.unionj.generator.mybatis.Constants.OUTPUT_DIR;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator
 * date 2020/11/22
 */
@Slf4j
public class MapperTestJavaGenerator extends DefaultGenerator {

  private String outputDir;
  private String mainClassReferenceName;
  private String mainClassName;
  private MapperInfo mapperInfo;

  public MapperTestJavaGenerator(String outputDir, String mainClassReferenceName, MapperInfo mapperInfo,
                                 boolean noDefaultComment) {
    super(noDefaultComment);
    List<String> parts = new ArrayList<>();
    parts.add("src");
    parts.add("test");
    parts.add("java");
    parts.addAll(Lists.newArrayList(StringUtils.split(mapperInfo.getMapperPackage(), ".")));
    this.outputDir = Paths.get(outputDir, parts.toArray(new String[]{})).toString();
    this.mainClassReferenceName = mainClassReferenceName;
    this.mainClassName = StringUtils.substringAfterLast(mainClassReferenceName, ".");
    this.mapperInfo = mapperInfo;
  }

  @Override
  public Map<String, Object> getInput() {
    Map<String, Object> input = new HashMap<>();
    input.put("packageName", this.mapperInfo.getMapperPackage());
    input.put("mainClassName", this.mainClassName);
    Set<String> imports = Sets.newHashSet(this.mainClassReferenceName);
    imports.addAll(this.mapperInfo.getImports());
    input.put("imports", imports);
    input.put("mapperName", this.mapperInfo.getMapperName());
    input.put("methods", this.mapperInfo.getMethods());
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
    return OUTPUT_DIR + File.separator + "mappertest.java.ftl";
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir) + File.separator + this.mapperInfo.getMapperName() + "Test.java";
  }

  @Override
  public String generate() {
    File file = new File(getOutputFile());
    if (file.exists()) {
      log.info(file + " already exists, skip generating");
      return getOutputFile();
    }
    try {
      return super.generateFormat();
    } catch (Exception ex) {
      log.error(ex.getMessage());
      log.error(this.mapperInfo.getMapperPackage() + "." + this.mapperInfo.getMapperName());
    }
    return getOutputFile();
  }
}
