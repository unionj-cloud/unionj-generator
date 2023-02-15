package cloud.unionj.generator;

import java.util.Map;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator
 *  date 2020/11/21
 */
public interface Generator {

  Map<String, Object> getInput();

  String getTemplate();

  String getOutputFile();

  String generateFormat();

  String generate();

}
