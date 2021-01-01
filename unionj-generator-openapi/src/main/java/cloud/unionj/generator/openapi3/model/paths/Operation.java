package cloud.unionj.generator.openapi3.model.paths;

import cloud.unionj.generator.openapi3.model.Callback;
import cloud.unionj.generator.openapi3.model.ExternalDocs;
import cloud.unionj.generator.openapi3.model.Security;
import cloud.unionj.generator.openapi3.model.servers.Server;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.model.paths
 * @date 2020/12/15
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

  public void tags(String tag) {
    this.tags.add(tag);
  }

  public void parameters(Parameter parameter) {
    this.parameters.add(parameter);
  }

}
