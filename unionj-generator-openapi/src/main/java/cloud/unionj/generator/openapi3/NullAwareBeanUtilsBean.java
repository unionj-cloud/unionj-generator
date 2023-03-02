package cloud.unionj.generator.openapi3;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

/**
 * @ProductName: Hundsun unionj-generator
 * @Description: 描述
 * @Author: wubin48435 wubin48435@hundsun.com
 * @CreateDate: 2023/3/2 10:55 PM
 * @Version: 1.0
 * @Copyright © 2023 Hundsun Technologies Inc. All Rights Reserved
 */
public class NullAwareBeanUtilsBean extends BeanUtilsBean {

  @Override
  public void copyProperty(Object dest, String name, Object value)
      throws IllegalAccessException, InvocationTargetException {
    if (value == null) return;
    super.copyProperty(dest, name, value);
  }

}
