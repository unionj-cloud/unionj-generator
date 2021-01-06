package cloud.unionj.generator.openapi3.dsl;

import lombok.SneakyThrows;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.dsl
 * date 2020/12/26
 */
public class PathHelper {

  @SneakyThrows
  public static <T extends IImporter> void doImport(Class<T> clazz) {
    clazz.newInstance().doImport();
  }
}
