package cloud.unionj.generator.backend.docparser.entity;

import cloud.unionj.generator.openapi3.dsl.SchemaHelper;
import cloud.unionj.generator.openapi3.model.Schema;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.mock.docparser.entity
 * date 2020/11/18
 */
@Data
public class ProtoProperty {

  public static final Builder UPLOAD_FILE_BUILDER = new Builder("MultipartFile").name("file");
  public static final Builder UPLOAD_FILES_BUILDER = new Builder("MultipartFile[]").name("files");
  public static final ProtoProperty STREAM = new Builder("ResponseEntity<byte[]>").build();

  private String name;
  private String requestParam;
  private String schemaType;
  private String in;
  private String type;
  private boolean required;
  private String defaultValue;

  private ProtoProperty() {
  }

  public static class Builder {
    private String name;
    private String in;
    private String type;
    private boolean required = true;
    private String defaultValue;
    private String schemaType;

    public Builder(String type) {
      this.type = type;
    }

    public Builder(Schema type) {
      this.type = type.javaType().replaceAll(SchemaHelper.LEFT_ARROW, "<").replaceAll(SchemaHelper.RIGHT_ARROW, ">");
      this.schemaType = type.getType();
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

    public Builder defaultValue(String defaultValue) {
      if (StringUtils.isNotBlank(defaultValue)) {
        this.defaultValue = defaultValue;
      }
      return this;
    }

    public ProtoProperty build() {
      ProtoProperty property = new ProtoProperty();
      property.name = this.name;
      property.type = this.type;
      property.in = this.in;
      property.required = this.required;
      property.defaultValue = this.defaultValue;
      property.schemaType = this.schemaType;
      return property;
    }
  }

}
