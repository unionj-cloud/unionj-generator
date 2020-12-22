package com.tsingxiao.unionj.generator.backend.docparser.entity;

import com.tsingxiao.unionj.generator.openapi3.model.Schema;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.backend.docparser.entity
 * @date:2020/12/21
 */
@Data
public class VoProperty {

  private String name;
  private String jsonProperty;
  private String type;
  protected int level;

  public VoProperty(String name, String jsonProperty, String type) {
    this.name = name;
    this.jsonProperty = jsonProperty;
    this.type = type;
  }

  public VoProperty(String name, String jsonProperty, Schema type) {
    this.name = name;
    this.jsonProperty = jsonProperty;
    setType(type);
  }

  private String getTypeByRef(String ref) {
    String key = ref.substring(ref.lastIndexOf("/") + 1);
    if (StringUtils.isBlank(key)) {
      return Object.class.getSimpleName();
    }
    return key.replaceAll("[^a-zA-Z]", "");
  }

  public void setType(Schema schema) {
    String tsType = this.deepSetType(schema);
    for (int i = 0; i < this.level; i++) {
      tsType += "[]";
    }
    this.type = tsType;
  }

  public void setType(String type) {
    this.type = type;
  }

  private String deepSetType(Schema schema) {
    String type = schema.getType();
    if (StringUtils.isBlank(type)) {
      return this.getTypeByRef(schema.getRef());
    }
    String tsType;
    switch (type) {
      case "boolean": {
        tsType = "boolean";
        break;
      }
      case "integer": {
        if (schema.getFormat().equals("int32")) {
          tsType = Integer.class.getSimpleName();
        } else {
          tsType = Long.class.getSimpleName();
        }
        break;
      }
      case "number": {
        if (schema.getFormat().equals("float")) {
          tsType = Float.class.getSimpleName();
        } else {
          tsType = Double.class.getSimpleName();
        }
        break;
      }
      case "string": {
        tsType = String.class.getSimpleName();
        break;
      }
      case "array": {
        this.level++;
        Schema items = schema.getItems();
        if (StringUtils.isNotBlank(items.getRef())) {
          tsType = this.getTypeByRef(items.getRef());
        } else {
          tsType = this.deepSetType(items);
        }
        break;
      }
      default: {
        tsType = Object.class.getSimpleName();
      }
    }
    return tsType;
  }
}
