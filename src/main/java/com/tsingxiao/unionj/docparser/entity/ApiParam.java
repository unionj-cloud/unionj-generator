package com.tsingxiao.unionj.docparser.entity;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.docparser.entity
 * @date:2020/11/18
 */
@Data
@AllArgsConstructor
public class ApiParam {

  private String name;
  private String in;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ApiParam apiParam = (ApiParam) o;
    return Objects.equal(name, apiParam.name) &&
        Objects.equal(in, apiParam.in);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name, in);
  }
}
