package com.tsingxiao.unionj.generator.mock.schemafaker.propertyfaker;

import java.lang.annotation.*;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.schemafaker
 * @date:2020/11/20
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Faker {
  Class<?> value();
}
