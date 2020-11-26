package com.tsingxiao.unionj.generator.service.docparser.entity;

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
  private int level;

  private String getSchemaNameByRef(String ref) {
    return ref.substring(ref.lastIndexOf("/") + 1);
  }

  public void deepSetType(Schema schema) {
    String type = schema.getType();
    String tsType = "";
    switch (type) {
      case SchemaTypeUtil.BOOLEAN_TYPE: {
        tsType = "boolean";
        break;
      }
      case SchemaTypeUtil.INTEGER_TYPE: {
        tsType = "number";
        break;
      }
      case SchemaTypeUtil.NUMBER_TYPE: {
        tsType = "number";
        break;
      }
      case "array": {
        this.level++;
        ArraySchema arraySchema = (ArraySchema) schema;
        Schema<?> items = arraySchema.getItems();
        if (StringUtils.isNotBlank(items.get$ref())) {
          tsType = this.getSchemaNameByRef(items.get$ref());
        } else {
          this.deepSetType(items);
        }
        break;
      }
      default: {
        tsType = "string";
        break;
      }
    }
    for (int i = 0; i < this.level; i++) {
      tsType += "[]";
    }
    this.setType(tsType);
  }

}
