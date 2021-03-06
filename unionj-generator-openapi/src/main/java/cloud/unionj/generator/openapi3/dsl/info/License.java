package cloud.unionj.generator.openapi3.dsl.info;

import cloud.unionj.generator.openapi3.expression.info.LicenseBuilder;

import java.util.function.Consumer;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.dsl
 * date 2020/12/14
 */
public class License extends Info {

  public static void license(Consumer<LicenseBuilder> consumer) {
    LicenseBuilder licenseBuilder = new LicenseBuilder();
    consumer.accept(licenseBuilder);
    cloud.unionj.generator.openapi3.model.info.License license = licenseBuilder.build();
    infoBuilder.license(license);
  }
}
