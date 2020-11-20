package com.tsingxiao.unionj.schemafaker.propertyfaker;

import java.lang.annotation.*;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.schemafaker
 * @date:2020/11/20
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Faker {
  Class<?> value();
}
