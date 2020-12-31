package cloud.unionj.generator.openapi3.expression.tags;


import cloud.unionj.generator.openapi3.model.tags.Tag;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.openapi3.expression
 * @date:2020/12/14
 */
public class TagBuilder {

  private Tag tag;

  public TagBuilder() {
    this.tag = new Tag();
  }

  public void name(String name) {
    this.tag.setName(name);
  }

  public void description(String description) {
    this.tag.setDescription(description);
  }

  public Tag build() {
    return this.tag;
  }

}
