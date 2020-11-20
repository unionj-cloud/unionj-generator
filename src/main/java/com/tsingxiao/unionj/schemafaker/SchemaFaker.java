package com.tsingxiao.unionj.schemafaker;

import com.fasterxml.jackson.databind.JsonNode;
import com.tsingxiao.unionj.schemafaker.propertyfaker.UnknownFormatException;
import io.swagger.v3.oas.models.media.Schema;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.schemafaker
 * @date:2020/11/19
 */
public interface SchemaFaker {
  JsonNode fakeFormat(String format) throws UnknownFormatException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

  JsonNode fakePrimitiveType(String type);

  JsonNode fakeObject(Schema schema);

  void setSchemas(Map<String, Schema> schemas);

  Schema getSchemaByRef(String ref);
}
