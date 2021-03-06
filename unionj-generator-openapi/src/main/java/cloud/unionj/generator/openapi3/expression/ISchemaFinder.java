package cloud.unionj.generator.openapi3.expression;

import cloud.unionj.generator.openapi3.model.Schema;

/**
 * @author created by wubin
 * @version v0.1
 * date 2020/12/28
 */
public interface ISchemaFinder {
  Schema find(String key);
}
