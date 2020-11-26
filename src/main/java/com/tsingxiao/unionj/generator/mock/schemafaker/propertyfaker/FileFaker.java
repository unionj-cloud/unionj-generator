package com.tsingxiao.unionj.generator.mock.schemafaker.propertyfaker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.schemafaker
 * @date:2020/11/20
 */
// TODO need test
public class FileFaker implements PropertyFaker {

  private Faker faker = new Faker();

  public FileFaker(Faker faker) {
    this.faker = faker;
  }

  public FileFaker() {
  }

  @SneakyThrows
  @Override
  public JsonNode fake() {
    File file = new File("/tmp/test.txt");
    FileUtils.write(file, "test", StandardCharsets.UTF_8);
    return TextNode.valueOf(file.getAbsolutePath());
  }
}
