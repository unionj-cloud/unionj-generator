package com.tsingxiao.unionj.generator.mock.schemafaker.propertyfaker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.github.javafaker.Faker;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.schemafaker
 * @date:2020/11/20
 */
public class CodeFaker implements PropertyFaker {

  private Faker faker = new Faker();

  public CodeFaker(Faker faker) {
    this.faker = faker;
  }

  public CodeFaker() {
  }

  @Override
  public JsonNode fake() {
    return IntNode.valueOf(0);
  }
}
