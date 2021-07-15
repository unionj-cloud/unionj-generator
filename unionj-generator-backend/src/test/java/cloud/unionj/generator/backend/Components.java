package cloud.unionj.generator.backend;

import cloud.unionj.generator.openapi3.model.Generic;
import cloud.unionj.generator.openapi3.model.Schema;

import static cloud.unionj.generator.openapi3.dsl.Generic.generic;
import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.Schema.schema;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.*;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.dsl.paths
 * date 2020/12/19
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

//  public static Generic UserDate = generic(gb -> {
//    gb.generic(User, dateTime);
//  });
//
//  public static Generic UserInteger = generic(gb -> {
//    gb.generic(User, int32);
//  });
//
//  public static Generic ResultDTOListUserDate = generic(gb -> {
//    gb.generic(ResultDTO, uniqueRefArray(UserDate.getxTitle()));
//  });
//
//  public static Generic ResultDTOListUserInteger = generic(gb -> {
//    gb.generic(ResultDTO, uniqueRefArray(UserInteger.getxTitle()));
//  });

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
    sb.description("排行榜");
    sb.properties("serialNo", int32);
    sb.properties("name", string);
    sb.properties("income", doubleNumer("累计收入"));
    sb.properties("quantity", int32("完成任务数量"));
  });

  public static Schema PageResult = schema(sb -> {
    sb.type("object");
    sb.title("PageResult");
    sb.dummy("com.treeyee.cloud.community.es.page.PageResult");
    sb.description("分页对象");
    sb.properties("items", ListT);
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("searchCount", bool);
    sb.properties("pages", int32("当前分页总页数"));
    sb.properties("offset", offsetProperty);
  });

//  public static Schema PageResultVOJobVO = generic(gb -> {
//    gb.generic(PageResult, ref(RankVO.getxTitle()));
//  });
//
//  public static Schema NestedSearchJobPageResult = generic(gb -> {
//    gb.generic(ResultDTO, ref(PageResultVOJobVO.getxTitle()));
//  });

//  public static Schema SearchJobPageResult = schema(sb -> {
//    sb.type("object");
//    sb.title("SearchJobPageResult");
//    sb.description("任务分页结果");
//    sb.properties("page", ref(NestedSearchJobPageResult.getxTitle()));
//  });

//  public static Generic ResultDTOPageResultVOJobVO = generic(gb -> {
//    gb.generic(ResultDTO, ref(SearchJobPageResult.getxTitle()));
//  });

  public static Schema BaseSearchCondition = schema(sb -> {
    sb.type("object");
    sb.title("BaseSearchCondition");
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("maxPage", maxPageProperty);
    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("offset", offsetProperty);
    sb.properties("status", int32Array("审批状态"));
  });

  public static Schema publishAtProperty = dateTime("发布时间");
  public static Schema endAtProperty = dateTime("截止时间");

  public static Schema ExternalVO = schema(sb -> {
    sb.type("object");
    sb.description("大厅友情分享任务");
    sb.title("ExternalVO");
    sb.properties("id", int32);
    sb.properties("name", string("友情分享任务名称"));
    sb.properties("from", string("任务来源"));
    sb.properties("createAt", publishAtProperty);
    sb.properties("endAt", endAtProperty);
    sb.properties("link", string("任务链接",
        "https://wj.treeyee.com/computer/index.html?surveyId=g8PizHDy&answerChannelId=343343818352885760"));
    sb.properties("describe", string("说明"));
    sb.properties("status", int32("状态, 已过期，未过期"));
  });

  public static Schema PageResultVOExternalVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(ExternalVO.getTitle()))));

  public static Schema ResultDTOPageResultVOExternalVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOExternalVO.getTitle()))));
}
