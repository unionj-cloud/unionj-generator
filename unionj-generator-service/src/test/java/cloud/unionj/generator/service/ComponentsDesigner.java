package cloud.unionj.generator.service;

import cloud.unionj.generator.openapi3.model.Generic;
import cloud.unionj.generator.openapi3.model.Schema;

import static cloud.unionj.generator.openapi3.dsl.Generic.generic;
import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.Schema.schema;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.*;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.openapi3.dsl.paths
 * @date:2020/12/19
 */
public class ComponentsDesigner {

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

  public static Schema ResultDTO = schema(sb -> {
    sb.type("object");
    sb.title("ResultDTO");
    sb.dummy("com.treeyee.cloud.community.es.page.ResultDTO");
    sb.properties("code", int32);
    sb.properties("msg", string);
    sb.properties("data", T);
  });

  public static Schema PageResultVO = schema(sb -> {
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

  public static Schema User = schema(sb -> {
    sb.type("object");
    sb.title("User");
    sb.properties("name", string);
    sb.properties("info", T);
  });

  public static Generic UserDate = generic(gb -> {
    gb.generic(User, dateTime);
  });

  public static Generic UserInteger = generic(gb -> {
    gb.generic(User, int32);
  });

  public static Generic ResultDTOListUserDate = generic(gb -> {
    gb.generic(ResultDTO, schema(sb -> {
      sb.type("array");
      sb.uniqueItems(true);
      sb.items(reference(rb -> {
        rb.ref(UserDate.getTitle());
      }));
    }));
  });

  public static Generic ResultDTOListUserInteger = generic(gb -> {
    gb.generic(ResultDTO, schema(sb -> {
      sb.type("array");
      sb.uniqueItems(true);
      sb.items(reference(rb -> {
        rb.ref(UserInteger.getTitle());
      }));
    }));
  });

  public static Schema PageSetVO = schema(sb -> {
    sb.type("object");
    sb.title("PageSetVO");
    sb.properties("items", SetT);
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("searchCount", bool);
    sb.properties("pages", int32("当前分页总页数"));
    sb.properties("offset", offsetProperty);
  });

  public static Schema RankVO = schema(sb -> {
    sb.type("object");
    sb.title("RankVO");
    sb.properties("serialNo", int32);
    sb.properties("avatar", string("头像url",
        "https://treeyee-spire.oss-cn-beijing.aliyuncs.com/cddf0ecc-a03e-4c16-8757-92bd7c4800ba1592982748849.jpg"));
    sb.properties("name", string);
    sb.properties("income", doubleNumer("累计收入"));
    sb.properties("quantity", int32("完成任务数量"));
  });

  public static Schema PageResult = schema(sb -> {
    sb.type("object");
    sb.title("PageResult");
    sb.dummy("com.treeyee.cloud.community.es.page.PageResult");
    sb.properties("items", ListT);
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("searchCount", bool);
    sb.properties("pages", int32("当前分页总页数"));
    sb.properties("offset", offsetProperty);
  });

  public static Schema PageResultVOJobVO = generic(gb -> {
    gb.generic(PageResult, reference(rb -> {
      rb.ref(RankVO.getTitle());
    }));
  });

  public static Schema NestedSearchJobPageResult = generic(gb -> {
    gb.generic(ResultDTO, reference(rb -> {
      rb.ref(PageResultVOJobVO.getTitle());
    }));
  });

  public static Schema SearchJobPageResult = schema(sb -> {
    sb.type("object");
    sb.title("SearchJobPageResult");
    sb.properties("page", ref(NestedSearchJobPageResult.getTitle()));
  });

  public static Generic ResultDTOPageResultVOJobVO = generic(gb -> {
    gb.generic(ResultDTO, reference(rb -> {
      rb.ref(SearchJobPageResult.getTitle());
    }));
  });
}
