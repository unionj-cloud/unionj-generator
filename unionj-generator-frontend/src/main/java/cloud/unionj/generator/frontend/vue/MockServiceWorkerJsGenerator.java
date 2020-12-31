package cloud.unionj.generator.frontend.vue;

import cloud.unionj.generator.GeneratorUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator
 * @date:2020/11/22
 */
public class MockServiceWorkerJsGenerator extends VueGenerator {

  private static final String CMD_TEMPL = "npx msw init ${public}";

  private String outputDir = OUTPUT_DIR;

  public MockServiceWorkerJsGenerator() {
  }

  public MockServiceWorkerJsGenerator(String outputDir) {
    this.outputDir = outputDir;
  }

  @Override
  public Map<String, Object> getInput() {
    return null;
  }

  @Override
  public String getTemplate() {
    return null;
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir + "/public/");
  }

  @SneakyThrows
  @Override
  public String generate() {
    String outputFile = getOutputFile();
    Map<String, String> data = new HashMap<String, String>();
    data.put("public", outputFile);

    String cmd = StrSubstitutor.replace(CMD_TEMPL, data);

    Runtime run = Runtime.getRuntime();
    Process pr = run.exec(cmd);
    pr.waitFor();
    BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
    String line = "";
    while ((line = buf.readLine()) != null) {
      System.out.println(line);
    }

    return getOutputFile();
  }
}
