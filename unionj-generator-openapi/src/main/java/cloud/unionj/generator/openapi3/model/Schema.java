package cloud.unionj.generator.openapi3.model;

import cloud.unionj.generator.openapi3.dsl.IGeneric;
import cloud.unionj.generator.openapi3.dsl.SchemaHelper;
import cloud.unionj.generator.openapi3.expression.ISchemaFinder;
import cloud.unionj.generator.openapi3.expression.SchemaBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.model
 * date 2020/12/14
 */
@Data
public class Schema implements IGeneric {

  @JsonIgnore
  private ISchemaFinder schemaFinder;

  @JsonProperty("$ref")
  private String ref;

  @JsonProperty("x-tree")
  private boolean tree;

  @JsonProperty("x-dummies")
  private List<String> dummies = new ArrayList<>();

  @JsonProperty("x-title")
  private String xTitle;

  public void setxTitle(String xTitle) {
    this.xTitle = xTitle;
    this.title = xTitle;
  }

  public String getxTitle() {
    return xTitle;
  }

  private String title;

  private String type;
  private String javaType;
  private Map<String, Schema> properties = new LinkedHashMap<>();
  private String format;
  private Schema items;
  private String description;

  @JsonProperty("x-dummy")
  private String dummy;

  // TODO
  private boolean uniqueItems;

  @JsonProperty("default")
  private Object defaultValue;

  private Object example;
  private boolean deprecated;
  private boolean nullable;
  private Discriminator discriminator;
  private Object maximum;
  private Object minimum;
  private Object exclusiveMaximum;
  private Object exclusiveMinimum;
  private Integer maxLength;
  private Integer minLength;
  private List<String> required = new ArrayList<>();

  @JsonProperty("enum")
  private List<String> enumValue = new ArrayList<>();

  @JsonProperty("x-enum-varnames")
  private List<String> enumVarnames = new ArrayList<>();

  private List<Schema> allOf = new ArrayList<>();
  private List<Schema> oneOf = new ArrayList<>();

  // TODO
  private List<Schema> anyOf;

  // TODO
  private List<Schema> not;

  // TODO
  @JsonDeserialize(using = AdditionalPropertiesDeserializer.class)
  private Schema additionalProperties;

  // TODO
  private Object pattern;

  public Schema() {
  }

  public Schema(ISchemaFinder schemaFinder) {
    this.schemaFinder = schemaFinder;
  }

  public void properties(String property, Schema schema) {
    this.properties.put(property, schema);
  }

  public void required(String required) {
    this.required.add(required);
  }

  public void enumValue(String enumValue) {
    this.enumValue.add(enumValue);
    this.enumVarnames.add(enumValue);
  }

  public void allOf(Schema schema) {
    this.allOf.add(schema);
  }

  public void oneOf(Schema schema) {
    this.oneOf.add(schema);
  }

  public String javaType() {
    if (this instanceof Generic) {
      return this.xTitle;
    }
    return this.deepSetType();
  }

  public String getTypeByRef(String ref) {
    String key = ref.substring(ref.lastIndexOf("/") + 1);
    if (StringUtils.isBlank(key)) {
      return Object.class.getSimpleName();
    }
    return key;
  }

  private String deepSetType() {
    if (StringUtils.isBlank(type)) {
      return this.getTypeByRef(ref);
    }
    String javaType;
    switch (type) {
      case "object": {
        if (additionalProperties != null) {
          String valueType = additionalProperties.deepSetType();
          javaType = "Map" + SchemaHelper.LEFT_ARROW + String.class.getSimpleName() + ", " + valueType + SchemaHelper.RIGHT_ARROW;
        } else if (format != null && format.equals("T")) {
          javaType = "T";
        } else {
          if (StringUtils.isNotBlank(this.getxTitle())) {
            javaType = this.getxTitle();
          } else {
            javaType = Object.class.getSimpleName();
          }
        }
        break;
      }
      case "boolean": {
        javaType = Boolean.class.getSimpleName();
        break;
      }
      case "integer": {
        if (format != null && format.equals("int32")) {
          javaType = Integer.class.getSimpleName();
        } else {
          javaType = Long.class.getSimpleName();
        }
        break;
      }
      case "number": {
        if (format != null && format.equals("float")) {
          javaType = Float.class.getSimpleName();
        } else {
          javaType = Double.class.getSimpleName();
        }
        break;
      }
      case "string": {
        if (format != null && format.equals("date-time")) {
          javaType = java.util.Date.class.getSimpleName();
        } else {
          javaType = String.class.getSimpleName();
        }
        break;
      }
      case "array": {
        if (!uniqueItems) {
          String elementType;
          if (StringUtils.isNotBlank(items.getRef())) {
            elementType = this.getTypeByRef(items.getRef());
          } else {
            elementType = items.deepSetType();
          }
          javaType = "List" + SchemaHelper.LEFT_ARROW + elementType + SchemaHelper.RIGHT_ARROW;
        } else {
          String elementType;
          if (StringUtils.isNotBlank(items.getRef())) {
            elementType = this.getTypeByRef(items.getRef());
          } else {
            elementType = items.deepSetType();
          }
          javaType = "Set" + SchemaHelper.LEFT_ARROW + elementType + SchemaHelper.RIGHT_ARROW;
        }
        break;
      }
      default: {
        javaType = Object.class.getSimpleName();
      }
    }
    return javaType;
  }

  @SneakyThrows
  public Generic generic(Schema schema) {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(this);
    Generic deepCopy = objectMapper.readValue(json, Generic.class);
    deepCopy.setSchemaFinder(schemaFinder);
    deepCopy.getProperties().forEach((k, v) -> {
      if (v.equals(SchemaHelper.T)) {
        deepCopy.properties(k, schema);
      } else if (v.equals(SchemaHelper.ListT)) {
        deepCopy.properties(k, new SchemaBuilder(null).type("array").items(schema).build());
      } else if (v.equals(SchemaHelper.SetT)) {
        deepCopy.properties(k, new SchemaBuilder(null).type("array").items(schema).uniqueItems(true).build());
      }
    });
    if (StringUtils.isNotBlank(schema.getxTitle())) {
      deepCopy.setxTitle(deepCopy.getxTitle() + SchemaHelper.LEFT_ARROW + schema.getxTitle() + SchemaHelper.RIGHT_ARROW);
    } else {
      String type = schema.javaType();
      deepCopy.setxTitle(deepCopy.getxTitle() + SchemaHelper.LEFT_ARROW + type + SchemaHelper.RIGHT_ARROW);
    }
    if (StringUtils.isBlank(schema.getType()) && schemaFinder != null) {
      String typeByRef = schema.getTypeByRef(schema.getRef());
      Schema typeByRefSchema = schemaFinder.find(typeByRef);
      if (typeByRefSchema.isDummy()) {
        deepCopy.getDummies().add(typeByRefSchema.getDummy());
      } else if (typeByRefSchema instanceof Generic) {
        Generic genericValue = (Generic) typeByRefSchema;
        deepCopy.getDummies().addAll(genericValue.getDummies());
      }
    } else {
      if (schema.isDummy()) {
        deepCopy.getDummies().add(schema.getDummy());
      } else if (schema instanceof Generic) {
        deepCopy.getDummies().addAll(schema.getDummies());
      }
    }
    if (deepCopy.isDummy()) {
      deepCopy.getDummies().add(deepCopy.getDummy());
      deepCopy.setDummy(null);
    }
    return deepCopy;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .toString();
  }

  public boolean isDummy() {
    return StringUtils.isNotBlank(this.dummy);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    Schema schema = (Schema) o;

    return new EqualsBuilder().append(ref, schema.ref).append(xTitle, schema.xTitle).append(type, schema.type).append(properties, schema.properties).append(format, schema.format).append(items, schema.items).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(ref).append(xTitle).append(type).append(properties).append(format).append(items).toHashCode();
  }
}
