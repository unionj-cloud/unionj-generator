package com.tsingxiao.unionj.generator.backend.docparser.entity;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.backend.docparser.entity
 * @date:2020/12/22
 */
public class ProtoFile extends ProtoProperty {

  public static class Builder {
    private String name = "file";
    private String type = "MultipartFile";
    private String in;
    private boolean required = true;
    private int level;

    public Builder() {

    }

    public Builder type(String type) {
      this.type = type;
      return this;
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

    public ProtoFile build() {
      ProtoFile property = new ProtoFile();
      property.name = this.name;
      property.type = this.type;
      property.in = this.in;
      property.required = this.required;
      property.level = this.level;
      return property;
    }
  }
}
