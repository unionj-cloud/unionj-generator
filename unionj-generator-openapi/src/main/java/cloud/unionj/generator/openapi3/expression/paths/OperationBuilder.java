package cloud.unionj.generator.openapi3.expression.paths;

import cloud.unionj.generator.openapi3.model.paths.Operation;
import cloud.unionj.generator.openapi3.model.paths.Parameter;
import cloud.unionj.generator.openapi3.model.paths.RequestBody;
import cloud.unionj.generator.openapi3.model.paths.Responses;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.expression
 *  date 2020/12/14
 */
public class OperationBuilder {

  private Operation operation;

  public OperationBuilder() {
    this.operation = new Operation();
  }

  public void summary(String summary) {
    this.operation.setSummary(summary);
  }

  public void description(String description) {
    this.operation.setDescription(description);
  }

  public void operationId(String operationId) {
    this.operation.setOperationId(operationId);
  }

  public void deprecated(boolean deprecated) {
    this.operation.setDeprecated(deprecated);
  }

  public void tags(String tag) {
    this.operation.tags(tag);
  }

  public void parameters(Parameter parameter) {
    this.operation.parameters(parameter);
  }

  public void requestBody(RequestBody requestBody) {
    this.operation.setRequestBody(requestBody);
  }

  // TODO
  public void responses(Responses responses) {
    this.operation.setResponses(responses);
  }

  public Operation build() {
    return this.operation;
  }
}
