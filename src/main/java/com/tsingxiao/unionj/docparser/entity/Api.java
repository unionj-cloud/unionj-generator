package com.tsingxiao.unionj.docparser.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.docparser.entity
 * @date:2020/11/18
 */
@Data
public class Api {
  private String baseUrl;
  private Map<String, List<ApiItem>> items;
}
