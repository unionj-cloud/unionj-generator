package com.tsingxiao.unionj.schemafaker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.models.media.Schema;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.schemafaker
 * @date:2020/11/19
 */
public interface SchemaFaker {
  JsonNode fakeFormat(String format);

  JsonNode fakePrimitiveType(String type);

  JsonNode fakeObject(Schema schema);

  JsonNode createRootObjectNode();
}
