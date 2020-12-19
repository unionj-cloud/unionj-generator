package com.tsingxiao.unionj.generator.mock.schemafaker;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.models.media.Schema;

import java.util.List;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.schemafaker
 * @date:2020/11/19
 */
public interface SchemaFaker {
  JsonNode fakeFormat(String format);

  <T> JsonNode fakeEnum(List<T> enums, String type);

  JsonNode fakePrimitiveType(String type);

  JsonNode fakeObject(Schema schema);

  void setSchemas(Map<String, Schema> schemas);

  Schema getSchemaByRef(String ref);
}
