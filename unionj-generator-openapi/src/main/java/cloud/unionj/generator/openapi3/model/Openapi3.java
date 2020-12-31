package cloud.unionj.generator.openapi3.model;

import cloud.unionj.generator.openapi3.model.info.Info;
import cloud.unionj.generator.openapi3.model.paths.Path;
import cloud.unionj.generator.openapi3.model.servers.Server;
import cloud.unionj.generator.openapi3.model.tags.Tag;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.openapi3.model
 * @date:2020/12/14
 */
@Data
public class Openapi3 {

  private String openapi = "3.0.2";
  private Info info;
  private List<Server> servers = new ArrayList<>();
  private List<Tag> tags = new ArrayList<>();
  private Map<String, Path> paths = new HashMap<>();
  private Components components;

  public void servers(Server server) {
    this.servers.add(server);
  }

  public void tags(Tag tag) {
    this.tags.add(tag);
  }

  public void paths(Path path) {
    this.paths.put(path.getEndpoint(), path);
  }

  // TODO
  private ExternalDocs externalDocs;
}
