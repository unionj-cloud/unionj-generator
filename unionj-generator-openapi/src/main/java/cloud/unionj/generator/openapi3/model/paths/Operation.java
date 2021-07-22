package cloud.unionj.generator.openapi3.model.paths;

import cloud.unionj.generator.openapi3.model.Callback;
import cloud.unionj.generator.openapi3.model.ExternalDocs;
import cloud.unionj.generator.openapi3.model.Security;
import cloud.unionj.generator.openapi3.model.servers.Server;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.model.paths
 * date 2020/12/15
 */
public class Operation {

  private List<String> tags = new ArrayList<>();
  private String summary;
  private String description;
  private String operationId;
  @JsonProperty("x-method-name")
  private String methodName;

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

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

  public Operation() {
  }

  public void tags(String tag) {
    this.tags.add(tag);
  }

  public void parameters(Parameter parameter) {
    this.parameters.add(parameter);
  }

  public List<String> getTags() {
    return this.tags;
  }

  public String getSummary() {
    return this.summary;
  }

  public String getDescription() {
    return this.description;
  }

  public String getOperationId() {
    return this.operationId;
  }

  public List<Parameter> getParameters() {
    return this.parameters;
  }

  public RequestBody getRequestBody() {
    return this.requestBody;
  }

  public Responses getResponses() {
    return this.responses;
  }

  public boolean isDeprecated() {
    return this.deprecated;
  }

  public ExternalDocs getExternalDocs() {
    return this.externalDocs;
  }

  public Map<String, Callback> getCallbacks() {
    return this.callbacks;
  }

  public List<Security> getSecurity() {
    return this.security;
  }

  public List<Server> getServers() {
    return this.servers;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setOperationId(String operationId) {
    this.operationId = operationId;
  }

  public void setParameters(List<Parameter> parameters) {
    this.parameters = parameters;
  }

  public void setRequestBody(RequestBody requestBody) {
    this.requestBody = requestBody;
  }

  public void setResponses(Responses responses) {
    this.responses = responses;
  }

  public void setDeprecated(boolean deprecated) {
    this.deprecated = deprecated;
  }

  public void setExternalDocs(ExternalDocs externalDocs) {
    this.externalDocs = externalDocs;
  }

  public void setCallbacks(Map<String, Callback> callbacks) {
    this.callbacks = callbacks;
  }

  public void setSecurity(List<Security> security) {
    this.security = security;
  }

  public void setServers(List<Server> servers) {
    this.servers = servers;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof Operation)) return false;
    final Operation other = (Operation) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$tags = this.getTags();
    final Object other$tags = other.getTags();
    if (this$tags == null ? other$tags != null : !this$tags.equals(other$tags)) return false;
    final Object this$summary = this.getSummary();
    final Object other$summary = other.getSummary();
    if (this$summary == null ? other$summary != null : !this$summary.equals(other$summary)) return false;
    final Object this$description = this.getDescription();
    final Object other$description = other.getDescription();
    if (this$description == null ? other$description != null : !this$description.equals(other$description))
      return false;
    final Object this$operationId = this.getOperationId();
    final Object other$operationId = other.getOperationId();
    if (this$operationId == null ? other$operationId != null : !this$operationId.equals(other$operationId))
      return false;
    final Object this$parameters = this.getParameters();
    final Object other$parameters = other.getParameters();
    if (this$parameters == null ? other$parameters != null : !this$parameters.equals(other$parameters)) return false;
    final Object this$requestBody = this.getRequestBody();
    final Object other$requestBody = other.getRequestBody();
    if (this$requestBody == null ? other$requestBody != null : !this$requestBody.equals(other$requestBody))
      return false;
    final Object this$responses = this.getResponses();
    final Object other$responses = other.getResponses();
    if (this$responses == null ? other$responses != null : !this$responses.equals(other$responses)) return false;
    if (this.isDeprecated() != other.isDeprecated()) return false;
    final Object this$externalDocs = this.getExternalDocs();
    final Object other$externalDocs = other.getExternalDocs();
    if (this$externalDocs == null ? other$externalDocs != null : !this$externalDocs.equals(other$externalDocs))
      return false;
    final Object this$callbacks = this.getCallbacks();
    final Object other$callbacks = other.getCallbacks();
    if (this$callbacks == null ? other$callbacks != null : !this$callbacks.equals(other$callbacks)) return false;
    final Object this$security = this.getSecurity();
    final Object other$security = other.getSecurity();
    if (this$security == null ? other$security != null : !this$security.equals(other$security)) return false;
    final Object this$servers = this.getServers();
    final Object other$servers = other.getServers();
    if (this$servers == null ? other$servers != null : !this$servers.equals(other$servers)) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof Operation;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $tags = this.getTags();
    result = result * PRIME + ($tags == null ? 43 : $tags.hashCode());
    final Object $summary = this.getSummary();
    result = result * PRIME + ($summary == null ? 43 : $summary.hashCode());
    final Object $description = this.getDescription();
    result = result * PRIME + ($description == null ? 43 : $description.hashCode());
    final Object $operationId = this.getOperationId();
    result = result * PRIME + ($operationId == null ? 43 : $operationId.hashCode());
    final Object $parameters = this.getParameters();
    result = result * PRIME + ($parameters == null ? 43 : $parameters.hashCode());
    final Object $requestBody = this.getRequestBody();
    result = result * PRIME + ($requestBody == null ? 43 : $requestBody.hashCode());
    final Object $responses = this.getResponses();
    result = result * PRIME + ($responses == null ? 43 : $responses.hashCode());
    result = result * PRIME + (this.isDeprecated() ? 79 : 97);
    final Object $externalDocs = this.getExternalDocs();
    result = result * PRIME + ($externalDocs == null ? 43 : $externalDocs.hashCode());
    final Object $callbacks = this.getCallbacks();
    result = result * PRIME + ($callbacks == null ? 43 : $callbacks.hashCode());
    final Object $security = this.getSecurity();
    result = result * PRIME + ($security == null ? 43 : $security.hashCode());
    final Object $servers = this.getServers();
    result = result * PRIME + ($servers == null ? 43 : $servers.hashCode());
    return result;
  }

  public String toString() {
    return "Operation(tags=" + this.getTags() + ", summary=" + this.getSummary() + ", description=" + this.getDescription() + ", operationId=" + this.getOperationId() + ", parameters=" + this.getParameters() + ", requestBody=" + this.getRequestBody() + ", responses=" + this.getResponses() + ", deprecated=" + this.isDeprecated() + ", externalDocs=" + this.getExternalDocs() + ", callbacks=" + this.getCallbacks() + ", security=" + this.getSecurity() + ", servers=" + this.getServers() + ")";
  }
}
