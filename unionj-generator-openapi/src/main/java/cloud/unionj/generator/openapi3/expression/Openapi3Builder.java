package cloud.unionj.generator.openapi3.expression;


import cloud.unionj.generator.openapi3.model.Components;
import cloud.unionj.generator.openapi3.model.ExternalDocs;
import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.openapi3.model.info.Info;
import cloud.unionj.generator.openapi3.model.paths.Path;
import cloud.unionj.generator.openapi3.model.servers.Server;
import cloud.unionj.generator.openapi3.model.tags.Tag;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.expression
 *  date 2020/12/14
 */
public class Openapi3Builder {

  private Openapi3 openapi3;

  public Openapi3Builder() {
    this.openapi3 = new Openapi3();
  }

  public void servers(Server server) {
    this.openapi3.servers(server);
  }

  public void tags(Tag tag) {
    this.openapi3.tags(tag);
  }

  public void paths(Path path) {
    this.openapi3.paths(path);
  }

  public void info(Info info) {
    this.openapi3.setInfo(info);
  }

  public void externalDocs(ExternalDocs externalDocs) {
    this.openapi3.setExternalDocs(externalDocs);
  }

  public void components(Components components) {
    this.openapi3.setComponents(components);
  }

  public Openapi3 build() {
    return this.openapi3;
  }

}
