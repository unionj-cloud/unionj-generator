package com.tsingxiao.unionj.generator.openapi3.dsl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import org.junit.Test;

import static com.tsingxiao.unionj.generator.openapi3.dsl.Generic.generic;
import static com.tsingxiao.unionj.generator.openapi3.dsl.Reference.reference;
import static com.tsingxiao.unionj.generator.openapi3.dsl.Schema.schema;
import static com.tsingxiao.unionj.generator.openapi3.dsl.SchemaHelper.*;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class GenericTest {

  @Data
  public static class ResultDTO<T> {
    private T data;
    private int code;
    private String msg;
  }

  @Data
  public static class ResultDTOBuilder<T> {
    public static <T> ResultDTO<T> ok(T data) {
      return restResult(data, 0, null);
    }

    private static <T> ResultDTO<T> restResult(T data, int code, String msg) {
      ResultDTO<T> apiResult = new ResultDTO<>();
      apiResult.setCode(code);
      apiResult.setData(data);
      apiResult.setMsg(msg);
      return apiResult;
    }
  }

  @Test
  public void Test1() throws JsonProcessingException {
    ResultDTO<String> abc = new ResultDTO<>();
  }

  @Test
  public void Test2() throws JsonProcessingException {
    com.tsingxiao.unionj.generator.openapi3.model.Generic ResultDTO = generic(sb -> {
      sb.type("object");
      sb.title("ResultDTO");
      sb.properties("code", int32);
      sb.properties("msg", string);
      sb.properties("data", T);
    });
    System.out.println(ResultDTO);

    com.tsingxiao.unionj.generator.openapi3.model.Generic User = generic(sb -> {
      sb.type("object");
      sb.title("User");
      sb.properties("name", string);
      sb.properties("info", T);
    });

    com.tsingxiao.unionj.generator.openapi3.model.Schema DateTime = schema(sb -> {
      sb.javaType(org.joda.time.DateTime.class.getCanonicalName());
    });

    com.tsingxiao.unionj.generator.openapi3.model.Generic ResultDTOUserListString = ResultDTO.generic(User.generic(stringArray));

    String title = ResultDTOUserListString.getTitle();
    System.out.println(title);
  }

  @Test
  public void Test3() throws JsonProcessingException {
    com.tsingxiao.unionj.generator.openapi3.model.Generic ResultDTO = generic(sb -> {
      sb.type("object");
      sb.title("ResultDTO");
      sb.properties("code", int32);
      sb.properties("msg", string);
      sb.properties("data", T);
    });
    System.out.println(ResultDTO);

    com.tsingxiao.unionj.generator.openapi3.model.Generic User = generic(sb -> {
      sb.type("object");
      sb.title("User");
      sb.properties("name", string);
      sb.properties("info", T);
    });

    com.tsingxiao.unionj.generator.openapi3.model.Generic ResultDTOUser = ResultDTO.generic(reference(rb -> {
      rb.ref(User.getTitle());
    }));

    String title = ResultDTOUser.getTitle();
    System.out.println(title);
  }

  @Test
  public void Test4() throws JsonProcessingException {
    com.tsingxiao.unionj.generator.openapi3.model.Generic ResultDTO = generic(sb -> {
      sb.type("object");
      sb.title("ResultDTO");
      sb.properties("code", int32);
      sb.properties("msg", string);
      sb.properties("data", T);
    });
    System.out.println(ResultDTO);

    com.tsingxiao.unionj.generator.openapi3.model.Generic User = generic(sb -> {
      sb.type("object");
      sb.title("User");
      sb.properties("name", string);
      sb.properties("info", T);
    });

    com.tsingxiao.unionj.generator.openapi3.model.Generic ResultDTOListUser = ResultDTO.generic(schema(sb -> {
      sb.type("array");
      sb.title("Set<User>");
      sb.items(reference(rb -> {
        rb.ref(User.getTitle());
      }));
    }));

    String title = ResultDTOListUser.getTitle();
    System.out.println(title);
  }

  @Test
  public void Test5() throws JsonProcessingException {
    com.tsingxiao.unionj.generator.openapi3.model.Generic ResultDTO = generic(sb -> {
      sb.type("object");
      sb.title("ResultDTO");
      sb.properties("code", int32);
      sb.properties("msg", string);
      sb.properties("data", T);
    }).generic(T);

    String title = ResultDTO.getTitle();
    System.out.println(title);

    System.out.println(ResultDTO.javaType());
  }
}