package com.tsingxiao.unionj.generator.backend.docparser.entity;

import com.tsingxiao.unionj.generator.openapi3.dsl.SchemaHelper;
import com.tsingxiao.unionj.generator.openapi3.model.Schema;
import lombok.Data;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.docparser.entity
 * @date:2020/11/18
 */
@Data
public class ProtoProperty {

  public static final Builder UPLOAD_FILE_BUILDER = new Builder("MultipartFile").name("file");
  public static final ProtoProperty STREAM = new Builder("ResponseEntity<byte[]>").build();

  private String name;
  private String in;
  private String type;
  private boolean required;

  private ProtoProperty() {
  }

  public static class Builder {
    private String name;
    private String in;
    private String type;
    private boolean required = true;

    public Builder(String type) {
      this.type = type;
    }

    public Builder(Schema type) {
      this.type = type.javaType().replaceAll(SchemaHelper.LEFT_ARROW, "<").replaceAll(SchemaHelper.RIGHT_ARROW, ">");
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

    public ProtoProperty build() {
      ProtoProperty property = new ProtoProperty();
      property.name = this.name;
      property.type = this.type;
      property.in = this.in;
      property.required = this.required;
      return property;
    }
  }

}
