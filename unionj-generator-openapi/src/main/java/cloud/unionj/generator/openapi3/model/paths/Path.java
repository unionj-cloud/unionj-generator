package cloud.unionj.generator.openapi3.model.paths;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.model
 * date 2020/12/14
 */
@Data
public class Path {

  @JsonProperty("x-endpoint")
  private String endpoint;

  private Operation get;

  private Operation post;

  private Operation put;

  private Operation delete;

  // TODO
  private List<Parameter> parameters;

  public List<String> returnTags() {
    List<String> result = new ArrayList<>();
    if (CollectionUtils.isEmpty(result)) {
      if (this.get != null) {
        result = this.get.getTags();
      }
    }
    if (CollectionUtils.isEmpty(result)) {
      if (this.post != null) {
        result = this.post.getTags();
      }
    }
    if (CollectionUtils.isEmpty(result)) {
      if (this.put != null) {
        result = this.put.getTags();
      }
    }
    if (CollectionUtils.isEmpty(result)) {
      if (this.delete != null) {
        result = this.delete.getTags();
      }
    }
    return result;
  }
}
