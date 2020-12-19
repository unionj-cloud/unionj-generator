package com.tsingxiao.unionj.generator.service.docparser.entity;

import com.google.common.base.Objects;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.parser.util.SchemaTypeUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.docparser.entity
 * @date:2020/11/18
 */
@Data
public class BizProperty {

  private String name;
  private String type;
  private String in;
  private int level;

  public BizProperty(String name, String type, String in, int level) {
    this.name = name;
    this.type = type;
    this.in = in;
    this.level = level;
  }

  public BizProperty(String name, String type, String in) {
    this.name = name;
    this.type = type;
    this.in = in;
  }

  public BizProperty(String type) {
    this.type = type;
  }

  public BizProperty() {
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

  private String deepSetType(Schema schema) {
    String type = schema.getType();
    if (StringUtils.isBlank(type)) {
      return this.getTypeByRef(schema.get$ref());
    }
    String tsType;
    switch (type) {
      case SchemaTypeUtil.BOOLEAN_TYPE: {
        tsType = TsTypeConstants.BOOLEAN;
        break;
      }
      case SchemaTypeUtil.INTEGER_TYPE: {
        tsType = TsTypeConstants.NUMBER;
        break;
      }
      case SchemaTypeUtil.NUMBER_TYPE: {
        tsType = TsTypeConstants.NUMBER;
        break;
      }
      case SchemaTypeUtil.STRING_TYPE: {
        tsType = TsTypeConstants.STRING;
        break;
      }
      case "array": {
        this.level++;
        ArraySchema arraySchema = (ArraySchema) schema;
        Schema<?> items = arraySchema.getItems();
        if (StringUtils.isNotBlank(items.get$ref())) {
          tsType = this.getTypeByRef(items.get$ref());
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
    BizProperty that = (BizProperty) o;
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
