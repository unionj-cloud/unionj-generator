package com.tsingxiao.unionj.generator.apidoc;

import com.tsingxiao.unionj.generator.GeneratorUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator
 * @date:2020/11/22
 */
public class IndexHtmlMdGenerator extends ApiDocGenerator {

  private static final String CMD_TEMPL = "widdershins --search false --language_tabs shell:Shell javascript:Javascript java:Java go:Go --summary ${doc} -o ${md}";

  private String doc;
  private String outputDir = OUTPUT_DIR;

  public IndexHtmlMdGenerator(String doc) {
    this.doc = doc;
  }

  public IndexHtmlMdGenerator(String doc, String outputDir) {
    this.doc = doc;
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
    return GeneratorUtils.getOutputDir(this.outputDir) + File.separator + "index.html.md";
  }

  @SneakyThrows
  @Override
  public String generate() {
    Map<String, String> data = new HashMap<String, String>();
    data.put("doc", GeneratorUtils.getOutputDir(this.doc));
    data.put("md", getOutputFile());

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
