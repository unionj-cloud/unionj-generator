package cloud.unionj.generator.openapi3.dsl;

import cloud.unionj.generator.openapi3.PathConfig;
import cloud.unionj.generator.openapi3.PathHelper;
import cloud.unionj.generator.openapi3.model.Openapi3;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static cloud.unionj.generator.openapi3.dsl.Generic.generic;
import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.Schema.schema;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.*;
import static cloud.unionj.generator.openapi3.dsl.info.Info.info;
import static cloud.unionj.generator.openapi3.dsl.info.Info.openapi3;
import static cloud.unionj.generator.openapi3.dsl.servers.Server.server;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.dsl
 * date 2020/12/14
 */
public class InfoTest {

  public static cloud.unionj.generator.openapi3.model.Schema ResultDTO = schema(sb -> {
    sb.type("object");
    sb.title("ResultDTO");
    sb.properties("code", int32);
    sb.properties("msg", string);
    sb.properties("data", T);
  });

  public static cloud.unionj.generator.openapi3.model.Schema PageResultVO = schema(sb -> {
    sb.type("object");
    sb.title("PageResultVO");
    sb.properties("items", ListT);
    sb.properties("searchCount", bool);
    sb.properties("pages", int32("当前分页总页数"));
  });

  public static cloud.unionj.generator.openapi3.model.Schema OptLogVO = schema(sb -> {
    sb.type("object");
    sb.title("OptLogVO");
    sb.properties("id", int32);
    sb.properties("eventStatus", string("事件状态"));
    sb.properties("eventType", string("事件类型"));
    sb.properties("eventObject", string("事件对象"));
    sb.properties("creatorUserId", int32);
    sb.properties("creatorCompanyId", int32);
    sb.properties("createAt", dateTime);
    sb.properties("name", string("操作人名字"));
  });

  public static cloud.unionj.generator.openapi3.model.Schema OptLogPageCondition = schema(sb -> {
    sb.type("object");
    sb.title("OptLogPageCondition");
    sb.properties("startTime", dateTime("开始时间"));
    sb.properties("endTime", dateTime("结束时间"));
    sb.properties("keyword", string("关键字搜索"));
    sb.properties("eventStatus", string("事件状态"));
    sb.properties("eventType", string("事件类型"));
  });

  public static cloud.unionj.generator.openapi3.model.Schema PageResultVOOptLogVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(OptLogVO.getTitle()))));
  public static cloud.unionj.generator.openapi3.model.Schema ResultDTOPageResultVOOptLogVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOOptLogVO.getTitle()))));

  @Test
  public void TestInfo1() throws JsonProcessingException {
    Openapi3 openAPI3 = openapi3(ob -> {
      info(ib -> {
        ib.title("用户管理模块");
        ib.version("v1.0.0");
      });

      server(sb -> {
        sb.url("http://unionj.cloud");
      });

      PathHelper.post("/admin/opt/log/list", PathConfig.builder()
          .summary("操作日志")
          .tags(new String[]{"admin_operate_log", "AdminOperateLog"})
          .reqSchema(OptLogPageCondition)
          .respSchema(ResultDTOPageResultVOOptLogVO)
          .build()
      );
    });
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    System.out.println(objectMapper.writeValueAsString(openAPI3));
  }
}
