package com.tsingxiao.unionj.generator.mock.schemafaker.propertyfaker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.javafaker.Faker;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.schemafaker
 * @date:2020/11/20
 */
public class CatFaker implements PropertyFaker {

  private Faker faker = new Faker();

  public CatFaker(Faker faker) {
    this.faker = faker;
  }

  public CatFaker() {
  }

  @Override
  public JsonNode fake() {
    return TextNode.valueOf(this.faker.cat().name());
  }
}