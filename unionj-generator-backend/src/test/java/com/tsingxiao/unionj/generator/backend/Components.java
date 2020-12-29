package com.tsingxiao.unionj.generator.backend;

import com.tsingxiao.unionj.generator.openapi3.model.Generic;
import com.tsingxiao.unionj.generator.openapi3.model.Schema;

import static com.tsingxiao.unionj.generator.openapi3.dsl.Reference.reference;
import static com.tsingxiao.unionj.generator.openapi3.dsl.Schema.schema;
import static com.tsingxiao.unionj.generator.openapi3.dsl.SchemaHelper.*;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl.paths
 * @date:2020/12/19
 */
public class Components {

  private static Schema sizeProperty = int32("每页条数，默认10，传-1查出全部数据");

  private static Schema currentProperty = int32("当前页，从1开始");

  private static Schema offsetProperty = int32("偏移量");

  private static Schema sortProperty = string("排序条件字符串：排序字段前使用'-'(降序)和'+'(升序)号表示排序规则，多个排序字段用','隔开",
      "+id,-create_at");

  private static Schema pageProperty = int32("当前页，从1开始");

  private static Schema limitProperty = int32("每页条数，默认10, 传-1查出全部数据", 10);

  private static Schema maxPageProperty = int32("导出结束页");

  private static Schema totalProperty = int64("总数，入参传入此参数则不再查询count，以此total为准");

  private static Schema topStatusProperty = int32("需要排在前的状态");

  public static Schema ShopRecommendApplyDetailCondition = schema(sb -> {
    sb.type("object");
    sb.title("ShopRecommendApplyDetailCondition");
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("maxPage", maxPageProperty);
    sb.properties("topStatus", topStatusProperty);
    sb.properties("shopName", string("店铺名称"));
    sb.properties("status", int32Array("审批状态"));
    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("offset", offsetProperty);
  });

  public static Schema ResultDTO = schema(sb -> {
    sb.type("object");
    sb.title("ResultDTO");
    sb.properties("code", int32);
    sb.properties("msg", string);
    sb.properties("data", T);
  });

  public static Schema User = schema(sb -> {
    sb.type("object");
    sb.title("User");
    sb.properties("name", string);
    sb.properties("info", T);
  });

  public static Generic UserDate = User.generic(dateTime);
  public static Generic UserInteger = User.generic(int32);

  public static Generic ResultDTOListUserDate = ResultDTO.generic(schema(sb -> {
    sb.type("array");
    sb.uniqueItems(true);
    sb.items(reference(rb -> {
      rb.ref(UserDate.getTitle());
    }));
  }));

  public static Generic ResultDTOListUserInteger = ResultDTO.generic(schema(sb -> {
    sb.type("array");
    sb.uniqueItems(true);
    sb.items(reference(rb -> {
      rb.ref(UserInteger.getTitle());
    }));
  }));
}
