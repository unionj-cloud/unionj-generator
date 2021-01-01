package cloud.unionj.generator.openapi3.model.paths;

import cloud.unionj.generator.openapi3.model.Example;
import cloud.unionj.generator.openapi3.model.Schema;
import lombok.Data;

import java.util.Map;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.model.paths
 *  date 2020/12/19
 */
@Data
public class MediaType {

  private Schema schema;

  private Object example;

  // TODO
  private Map<String, Example> examples;

  // TODO
  private Map<String, Encoding> encoding;

}
