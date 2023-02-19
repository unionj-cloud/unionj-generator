package cloud.unionj.generator.backend.utils;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @ProductName: Hundsun unionj-generator
 * @Description: 描述
 * @Author: wubin48435 wubin48435@hundsun.com
 * @CreateDate: 2023/2/19 5:32 PM
 * @Version: 1.0
 * @Copyright © 2023 Hundsun Technologies Inc. All Rights Reserved
 */
public class Utils {
  public static String cleanClassName(String name) {
    return Arrays.asList(StringUtils.trim(name)
                                    .split("[^a-zA-Z0-9_<>]"))
                 .stream()
                 .filter(StringUtils::isNotBlank)
                 .map(key -> {
                   CaseFormat originalFormat = CaseFormat.LOWER_CAMEL;
                   if (Character.isUpperCase(key.charAt(0)) && key.contains("_")) {
                     originalFormat = CaseFormat.UPPER_UNDERSCORE;
                   } else if (Character.isUpperCase(key.charAt(0))) {
                     originalFormat = CaseFormat.UPPER_CAMEL;
                   } else if (key.contains("_")) {
                     originalFormat = CaseFormat.LOWER_UNDERSCORE;
                   } else if (key.contains("-")) {
                     originalFormat = CaseFormat.LOWER_HYPHEN;
                   }
                   key = originalFormat.to(CaseFormat.UPPER_CAMEL, key);
                   return key;
                 })
                 .reduce("", (x, y) -> x + y);
  }
}
