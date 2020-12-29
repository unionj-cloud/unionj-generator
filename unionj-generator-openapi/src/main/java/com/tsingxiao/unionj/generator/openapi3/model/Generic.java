package com.tsingxiao.unionj.generator.openapi3.model;

import com.google.gson.Gson;
import com.tsingxiao.unionj.generator.openapi3.dsl.SchemaHelper;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.model
 * @date:2020/12/14
 */
@Data
public class Generic extends Schema {

  @SneakyThrows
  public Generic generic(Schema schema) {
    Gson gson = new Gson();
    Generic deepCopy = gson.fromJson(gson.toJson(this), Generic.class);
    List<String> genericPropertyList = new ArrayList<>();
    deepCopy.getProperties().forEach((k, v) -> {
      if (v.equals(SchemaHelper.T)) {
        genericPropertyList.add(k);
      }
    });
    genericPropertyList.stream().forEach(property -> {
      deepCopy.properties(property, schema);
    });
    if (StringUtils.isNotBlank(schema.getTitle())) {
      deepCopy.setTitle(deepCopy.getTitle() + SchemaHelper.LEFT_ARROW + schema.getTitle() + SchemaHelper.RIGHT_ARROW);
    } else {
      String type = schema.javaType();
      deepCopy.setTitle(deepCopy.getTitle() + SchemaHelper.LEFT_ARROW + type + SchemaHelper.RIGHT_ARROW);
    }
//    if (StringUtils.isNotBlank(schema.getTitle())) {
//      deepCopy.setTitle(deepCopy.getTitle() + SchemaHelper.LEFT_ARROW + schema.getTitle() + SchemaHelper.RIGHT_ARROW);
//    } else if (StringUtils.isNotBlank(schema.getJavaType())) {
//      String javaType = schema.getJavaType();
//      String[] split = StringUtils.split(javaType, ".");
//      String type = split[split.length - 1];
//      deepCopy.setTitle(deepCopy.getTitle() + SchemaHelper.LEFT_ARROW + type + SchemaHelper.RIGHT_ARROW);
//    } else if (StringUtils.isNotBlank(schema.getRef())) {
//      String ref = schema.getRef();
//      String[] split = StringUtils.split(ref, "/");
//      String type = split[split.length - 1];
//      deepCopy.setTitle(deepCopy.getTitle() + SchemaHelper.LEFT_ARROW + type + SchemaHelper.RIGHT_ARROW);
//    } else if(schema.getType().equals("array")) {
//      Schema items = schema.getItems();
//
//    }
    return deepCopy;
  }

}
