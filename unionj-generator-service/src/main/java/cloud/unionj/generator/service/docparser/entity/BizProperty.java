package cloud.unionj.generator.service.docparser.entity;

import cloud.unionj.generator.openapi3.model.Schema;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.mock.docparser.entity
 * date 2020/11/18
 */
@Data
public class BizProperty {

  private String name;
  private String type;
  private String in;
  private boolean required = true;
  private String doc;
  private List<String> docs;

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
    this.type = this.deepSetType(schema);
  }

  public void setType(String type) {
    this.type = type;
  }

  @SneakyThrows
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
        String format = schema.getFormat();
        if (format != null && format.equals("binary")) {
          tsType = TsTypeConstants.BLOB;
        } else {
          tsType = TsTypeConstants.STRING;
        }
        break;
      }
      case "array": {
        Schema items = schema.getItems();
        if (StringUtils.isNotBlank(items.getRef())) {
          tsType = this.getTypeByRef(items.getRef());
        } else if (StringUtils.isNotBlank(items.getType())) {
          tsType = this.deepSetType(items);
        } else {
          tsType = "";
        }
        tsType += "[]";
        break;
      }
      case "object": {
        if (StringUtils.isNotBlank(schema.getxTitle())) {
          tsType = schema.getxTitle().replaceAll("[^a-zA-Z]", "");
        } else {
          tsType = BizType.fromSchema(schema, BizType.EnumAs.DOC).toCode();
        }
        break;
      }
      default: {
        tsType = TsTypeConstants.ANY;
      }
    }
    return tsType;
  }
}
