package com.tsingxiao.unionj.generator.backend.springboot.docparser.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.backend.springboot.docparser.entity
 * @date:2020/12/21
 */
@Data
public class Backend {

  List<Vo> voList;

  // TODO
  List<Controller> controllerList;

}
