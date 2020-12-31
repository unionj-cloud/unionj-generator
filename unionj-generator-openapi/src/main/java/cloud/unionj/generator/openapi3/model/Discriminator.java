package cloud.unionj.generator.openapi3.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.openapi3.model
 * @date:2020/12/16
 */
@Data
public class Discriminator {

  private String propertyName;
  private Map<String, String> mapping = new HashMap<>();

  public void setMapping(String key, String value) {
    this.mapping.put(key, value);
  }
}
