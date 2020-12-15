package com.tsingxiao.unionj.generator.openapi3.dsl.info;

import com.tsingxiao.unionj.generator.openapi3.eval.Evaluator;
import com.tsingxiao.unionj.generator.openapi3.expression.LicenseBuilder;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/14
 */
public class License extends Info {

  private static LicenseBuilder licenseBuilder;

  public static void license(Evaluator evaluator) {
    licenseBuilder = new LicenseBuilder();
    evaluator.eval();
    com.tsingxiao.unionj.generator.openapi3.model.info.License license = licenseBuilder.build();
    infoBuilder.license(license);
  }

  public static void name(String name) {
    licenseBuilder.name(name);
  }

  public static void url(String url) {
    licenseBuilder.url(url);
  }
}
