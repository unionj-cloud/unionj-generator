package com.tsingxiao.unionj.generator.service;

import com.tsingxiao.unionj.generator.GeneratorUtils;
import com.tsingxiao.unionj.generator.service.entity.Server;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator
 * @date:2020/11/22
 */
public class BizServiceTsGenerator extends ServiceGenerator {

  private Server server;
  private String outputDir = OUTPUT_DIR;

  public BizServiceTsGenerator(Server server) {
    this.server = server;
  }

  public BizServiceTsGenerator(Server server, String outputDir) {
    this.server = server;
    this.outputDir = outputDir;
  }

  @Override
  public Map<String, Object> getInput() {
    Map<String, Object> input = new HashMap<>();
    input.put("serverName", this.server.getName());
    return input;
  }

  @Override
  public String getTemplate() {
    return OUTPUT_DIR + File.separator + "BizService.ts.ftl";
  }

  @Override
  public String getOutputFile() {
    return GeneratorUtils.getOutputDir(this.outputDir) + File.separator + "BizService.ts";
  }

}
