package com.tsingxiao.unionj.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsingxiao.unionj.docparser.entity.Api;
import com.tsingxiao.unionj.docparser.entity.ApiItem;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator
 * @date:2020/11/22
 */
public class HandlersGenerator extends DefaultApiGenerator {

  private Api api;
  private String outputDir;
  private ObjectMapper objectMapper = new ObjectMapper();

  public HandlersGenerator(Api api) {
    this.api = api;
  }

  public HandlersGenerator(Api api, String outputDir) {
    this.api = api;
    this.outputDir = outputDir;
  }

  public HandlersGenerator(Api api, String outputDir, ObjectMapper objectMapper) {
    this.api = api;
    this.outputDir = outputDir;
    this.objectMapper = objectMapper;
  }

  @Override
  public Map<String, Object> getInput() {
    Map<String, Object> input = new HashMap<String, Object>();
    input.put("baseUrl", this.api.getBaseUrl());

    List<ApiItemVo> apiItemVoList = this.api.getItems().values().stream().reduce(new ArrayList<>(), (x, y) -> {
      x.addAll(y);
      return x;
    }).stream().map(ApiItem::toApiItemVo).collect(Collectors.toList());

    input.put("apis", apiItemVoList);
    return input;
  }

  @Override
  public String getTemplate() {
    return "handlers.js.ftl";
  }

  @Override
  public String getOutputFile() {
    String outputDir = this.outputDir;
    if (StringUtils.isBlank(outputDir)) {
      outputDir = System.getProperty("user.dir");
    }
    return outputDir + "/handlers.js";
  }
}
