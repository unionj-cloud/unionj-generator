package cloud.unionj.generator.backend.springboot.export;

import cloud.unionj.generator.openapi3.model.Schema;

import static cloud.unionj.generator.openapi3.dsl.Generic.generic;
import static cloud.unionj.generator.openapi3.dsl.Schema.schema;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.*;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.gossip.gen
 * @date:2021/9/15
 */
public class ExportComponents {

  public static Schema ResultDTO = schema(sb -> {
    sb.type("object");
    sb.title("ResultDTO");
    sb.properties("code", int32);
    sb.properties("msg", string);
    sb.properties("data", T);
  });

  public static Schema ResultDTOString = generic(gb -> gb.generic(ResultDTO, string("下载链接")));

}
