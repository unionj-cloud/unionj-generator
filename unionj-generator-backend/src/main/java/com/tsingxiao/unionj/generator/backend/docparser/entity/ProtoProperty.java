package com.tsingxiao.unionj.generator.backend.docparser.entity;

import com.google.common.base.Objects;
import com.tsingxiao.unionj.generator.openapi3.model.Schema;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.docparser.entity
 * @date:2020/11/18
 */
@Data
public class ProtoProperty {

  protected String name;
  protected String type;
  protected String in;
  protected boolean required;
  protected int level;

  public ProtoProperty(String name, String type, String in, int level) {
    this.name = name;
    this.type = type;
    this.in = in;
    this.level = level;
  }

  public ProtoProperty(String name, String type, String in) {
    this.name = name;
    this.type = type;
    this.in = in;
  }

  public ProtoProperty(String type) {
    this.type = type;
  }

  public ProtoProperty() {
  }

  public static class Builder {
    private String name;
    private String type;
    private String in;
    private boolean required = true;
    private int level;

    public Builder(String type) {
      this.type = type;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder in(String in) {
      this.in = in;
      return this;
    }

    public Builder required(boolean required) {
      this.required = required;
      return this;
    }

    public Builder level(int level) {
      this.level = level;
      return this;
    }

    public ProtoProperty build() {
      ProtoProperty property = new ProtoProperty();
      property.name = this.name;
      property.type = this.type;
      property.in = this.in;
      property.required = this.required;
      property.level = this.level;
      return property;
    }
  }

  private String getTypeByRef(String ref) {
    String key = ref.substring(ref.lastIndexOf("/") + 1);
    if (StringUtils.isBlank(key)) {
      return TsTypeConstants.ANY;
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
        tsType = TsTypeConstants.BOOLEAN;
        break;
      }
      case "integer": {
        tsType = TsTypeConstants.NUMBER;
        break;
      }
      case "number": {
        tsType = TsTypeConstants.NUMBER;
        break;
      }
      case "string": {
        tsType = TsTypeConstants.STRING;
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
        tsType = TsTypeConstants.ANY;
      }
    }
    return tsType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProtoProperty that = (ProtoProperty) o;
    return level == that.level &&
        Objects.equal(name, that.name) &&
        Objects.equal(type, that.type) &&
        Objects.equal(in, that.in);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name, type, in, level);
  }
}
