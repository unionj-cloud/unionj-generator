package cloud.unionj.generator.service;

import cloud.unionj.generator.openapi3.dsl.Reference;
import cloud.unionj.generator.openapi3.dsl.Schema;
import cloud.unionj.generator.openapi3.model.Generic;

import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.*;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.openapi3.dsl.paths
 * @date:2020/12/19
 */
public class Components {

  private static cloud.unionj.generator.openapi3.model.Schema sizeProperty = int32("每页条数，默认10，传-1查出全部数据");

  private static cloud.unionj.generator.openapi3.model.Schema currentProperty = int32("当前页，从1开始");

  private static cloud.unionj.generator.openapi3.model.Schema offsetProperty = int32("偏移量");

  private static cloud.unionj.generator.openapi3.model.Schema sortProperty = string("排序条件字符串：排序字段前使用'-'(降序)和'+'(升序)号表示排序规则，多个排序字段用','隔开",
      "+id,-create_at");

  private static cloud.unionj.generator.openapi3.model.Schema pageProperty = int32("当前页，从1开始");

  private static cloud.unionj.generator.openapi3.model.Schema limitProperty = int32("每页条数，默认10, 传-1查出全部数据", 10);

  private static cloud.unionj.generator.openapi3.model.Schema maxPageProperty = int32("导出结束页");

  private static cloud.unionj.generator.openapi3.model.Schema totalProperty = int64("总数，入参传入此参数则不再查询count，以此total为准");

  private static cloud.unionj.generator.openapi3.model.Schema topStatusProperty = int32("需要排在前的状态");

  public static cloud.unionj.generator.openapi3.model.Schema ShopRecommendApplyDetailCondition = Schema.schema(sb -> {
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

  public static cloud.unionj.generator.openapi3.model.Schema ResultDTO = Schema.schema(sb -> {
    sb.type("object");
    sb.title("ResultDTO");
    sb.properties("code", int32);
    sb.properties("msg", string);
    sb.properties("data", T);
  });

  public static cloud.unionj.generator.openapi3.model.Schema PageResultVO = Schema.schema(sb -> {
    sb.type("object");
    sb.title("PageResultVO");
    sb.properties("items", ListT);
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("searchCount", bool);
    sb.properties("pages", int32("当前分页总页数"));
    sb.properties("offset", offsetProperty);
  });

  public static cloud.unionj.generator.openapi3.model.Schema User = Schema.schema(sb -> {
    sb.type("object");
    sb.title("User");
    sb.properties("name", string);
    sb.properties("info", T);
  });

  public static Generic UserDate = User.generic(dateTime);
  public static Generic UserInteger = User.generic(int32);

  public static Generic ResultDTOListUserDate = ResultDTO.generic(Schema.schema(sb -> {
    sb.type("array");
    sb.uniqueItems(true);
    sb.items(Reference.reference(rb -> {
      rb.ref(UserDate.getTitle());
    }));
  }));

  public static Generic ResultDTOListUserInteger = ResultDTO.generic(Schema.schema(sb -> {
    sb.type("array");
    sb.uniqueItems(true);
    sb.items(Reference.reference(rb -> {
      rb.ref(UserInteger.getTitle());
    }));
  }));

  public static Generic PageResultVOShopRecommendApplyDetailVO = PageResultVO.generic(Reference.reference(rb -> {
    rb.ref(UserDate.getTitle());
  }));

  public static cloud.unionj.generator.openapi3.model.Schema ResultDTOString = ResultDTO.generic(string);
}
