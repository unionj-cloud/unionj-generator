package com.tsingxiao.unionj.generator.service;

import com.tsingxiao.unionj.generator.GeneratorUtils;
import com.tsingxiao.unionj.generator.service.docparser.ServiceDocParser;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizServer;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizService;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator
 * @date:2020/11/22
 */
public class ServiceFolderGenerator extends ServiceGenerator {

  private String doc;
  private String outputDir = OUTPUT_DIR;

  public ServiceFolderGenerator(String doc) {
    this.doc = doc;
  }

  public ServiceFolderGenerator(String doc, String outputDir) {
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
    return GeneratorUtils.getOutputDir(this.outputDir);
  }

  @SneakyThrows
  @Override
  public String generate() {
    ServiceDocParser docParser = new ServiceDocParser(this.doc);
    BizServer bizServer = docParser.parse();

    BizServiceTsGenerator bizServiceTsGenerator = new BizServiceTsGenerator(bizServer.getName());
    bizServiceTsGenerator.generate();

    TypesTsGenerator typesTsGenerator = new TypesTsGenerator(bizServer.getTypes());
    typesTsGenerator.generate();

    for (BizService bizService : bizServer.getServices()) {
      ServiceTsGenerator serviceTsGenerator = new ServiceTsGenerator(bizService);
      serviceTsGenerator.generate();
    }

    String outputFile = GeneratorUtils.getOutputDir("output") + File.separator + OUTPUT_DIR + ".zip";
    String sourceFile = getOutputFile();
    return GeneratorUtils.generateFolder(sourceFile, outputFile);
  }
}
