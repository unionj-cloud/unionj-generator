package cloud.unionj.generator.backend.springboot;

/**
 * @author: created by wubin
 * cloud.unionj.generator.backend.springboot
 * 2021/9/24
 */
public enum Mode {
  /**
   * Only generate proto and vo packages
   */
  BASIC,
  /**
   * Generate proto, vo, controller and service packages
   */
  FULL;
}
