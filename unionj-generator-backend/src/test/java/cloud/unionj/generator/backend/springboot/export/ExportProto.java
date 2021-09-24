package cloud.unionj.generator.backend.springboot.export;

import cloud.unionj.generator.openapi3.PathConfig;
import cloud.unionj.generator.openapi3.PathHelper;
import cloud.unionj.generator.openapi3.dsl.IImporter;
import cloud.unionj.generator.openapi3.expression.paths.ParameterBuilder;
import cloud.unionj.generator.openapi3.model.paths.Parameter;

import static cloud.unionj.generator.backend.springboot.export.ExportComponents.ResultDTOString;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.int64Array;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.gossip.gen
 * @date:2021/9/15
 */
public class ExportProto implements IImporter {
  @Override
  public void doImport() {
    PathHelper.get("/export/case", PathConfig.builder()
        .summary("导出接口")
        .tags(new String[]{"导出"})
        .parameters(new Parameter[]{
            ParameterBuilder.builder().name("id").description("ID").in(Parameter.InEnum.QUERY).required(false).schema(int64Array).build(),
        })
        .respSchema(ResultDTOString)
        .build());
  }
}
