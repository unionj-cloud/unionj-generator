package com.tsingxiao.unionj.generator.service.docparser.entity;

import com.google.common.base.CaseFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.docparser.entity
 * @date:2020/11/18
 */
public class BizRouter {
  @Getter
  private String name;
  @Getter
  private String endpoint;
  @Getter
  private String httpMethod;

  @Setter
  @Getter
  private BizProperty reqBody;
  @Setter
  @Getter
  private BizProperty respData = new BizProperty(TsTypeConstants.ANY);
  @Setter
  @Getter
  private List<BizProperty> pathParams;
  @Setter
  @Getter
  private List<BizProperty> queryParams;

  // TODO need implementation
  @Setter
  @Getter
  private List<BizProperty> headerParams;

  public BizRouter(String endpoint, String httpMethod) {
    this.endpoint = endpoint;
    this.httpMethod = httpMethod;
    this.setName();
  }

  public BizRouter(String endpoint, String httpMethod, String name) {
    this.endpoint = endpoint;
    this.httpMethod = httpMethod;
    if (StringUtils.isNotBlank(name)) {
      this.name = name;
    } else {
      this.setName();
    }
  }

  private void setName() {
    if (StringUtils.isBlank(this.getEndpoint()) || StringUtils.isBlank(this.getHttpMethod())) {
      return;
    }
    String endpoint = this.getEndpoint().replaceAll("[^a-zA-Z]", "_").toLowerCase();
    endpoint = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, endpoint);
    this.name = this.getHttpMethod().toLowerCase() + endpoint;
  }
}
