package com.tsingxiao.unionj.generator.openapi3.expression;

import com.tsingxiao.unionj.generator.openapi3.ExceptionHelper;
import com.tsingxiao.unionj.generator.openapi3.dsl.SchemaHelper;
import com.tsingxiao.unionj.generator.openapi3.model.Generic;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.expression
 * @date:2020/12/16
 */
public class GenericBuilder extends SchemaBuilder {

  public GenericBuilder() {
    this.schema = new Generic();
  }

  @SneakyThrows
  @Override
  public Generic build() {
    List<String> genericPropertyList = new ArrayList<>();
    this.schema.getProperties().forEach((k, v) -> {
      if (v.equals(SchemaHelper.T)) {
        genericPropertyList.add(k);
      }
    });
    if (CollectionUtils.isEmpty(genericPropertyList)) {
      throw ExceptionHelper.NotGenericSchemaException;
    }
    return (Generic) this.schema;
  }
}
