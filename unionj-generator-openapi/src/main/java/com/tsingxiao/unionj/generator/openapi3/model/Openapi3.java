package com.tsingxiao.unionj.generator.openapi3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tsingxiao.unionj.generator.openapi3.model.info.Info;
import com.tsingxiao.unionj.generator.openapi3.model.paths.Path;
import com.tsingxiao.unionj.generator.openapi3.model.servers.Server;
import com.tsingxiao.unionj.generator.openapi3.model.tags.Tag;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.model
 * @date:2020/12/14
 */
@Data
public class Openapi3 {

  private String openapi = "3.0.2";
  private Info info;

  // TODO
  private ExternalDocs externalDocs;

  @JsonIgnore
  private List<Server> servers = new ArrayList<>();

  @JsonIgnore
  private List<Tag> tags = new ArrayList<>();
  private Map<String, Path> paths = new HashMap<>();
  private Components components;

  public void setServers(Server server) {
    this.servers.add(server);
  }

  public void setTags(Tag tag) {
    this.tags.add(tag);
  }

  public void setPaths(Path path) {
    this.paths.put(path.getEndpoint(), path);
  }
}
