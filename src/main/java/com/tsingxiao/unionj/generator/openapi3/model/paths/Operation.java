package com.tsingxiao.unionj.generator.openapi3.model.paths;

import com.tsingxiao.unionj.generator.openapi3.model.*;
import com.tsingxiao.unionj.generator.openapi3.model.servers.Server;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.model.paths
 * @date:2020/12/15
 */
@Data
public class Operation {

  private List<String> tags = new ArrayList<>();
  private String summary;
  private String description;
  private String operationId;
  private List<Parameter> parameters = new ArrayList<>();
  private RequestBody requestBody;
  private Responses responses;
  private boolean deprecated;

  // TODO
  private ExternalDocs externalDocs;

  // TODO
  private Map<String, Callback> callbacks;

  // TODO
  private List<Security> security;

  // TODO
  private List<Server> servers;

  public void setTags(String tag) {
    this.tags.add(tag);
  }

  public void setParameters(Parameter parameter) {
    this.parameters.add(parameter);
  }

}
