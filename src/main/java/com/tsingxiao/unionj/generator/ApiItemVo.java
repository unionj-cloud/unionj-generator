package com.tsingxiao.unionj.generator;

import com.tsingxiao.unionj.generator.mock.docparser.entity.ApiItem;
import lombok.Data;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator
 * @date:2020/11/22
 */
@Data
public class ApiItemVo extends ApiItem {
  private String responseStr;
}
