package com.tsingxiao.unionj.generator.openapi3.model.paths;

import com.tsingxiao.unionj.generator.openapi3.model.RequestBody;
import com.tsingxiao.unionj.generator.openapi3.model.Responses;
import com.tsingxiao.unionj.generator.openapi3.model.Security;
import lombok.Data;

import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.model
 * @date:2020/12/14
 */
@Data
public class Post {

  private List<String> tags;
  private String summary;
  private String description;
  private String operationId;
  private RequestBody requestBody;
  private Responses responses;
  private List<Security> security;

}
