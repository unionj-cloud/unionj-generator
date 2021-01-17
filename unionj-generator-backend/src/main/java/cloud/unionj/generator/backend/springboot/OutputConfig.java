package cloud.unionj.generator.backend.springboot;

import org.apache.commons.lang3.StringUtils;

/**
 * @author created by tqccc
 * @version v0.0.1
 * description: cloud.unionj.generator.backend.springboot
 * date:2021/1/17
 */
public class OutputConfig {
  private String packageName;
  private String outputDir;

  public OutputConfig(String packageName, String outputDir) {
    this.packageName = packageName;
    this.outputDir = outputDir;

    validate();
  }

  public String getPackageName() {
    return packageName;
  }

  public String getOutputDir() {
    return outputDir;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public void setOutputDir(String outputDir) {
    this.outputDir = outputDir;
  }

  public void validate() throws UnsupportedOperationException {
    if (StringUtils.isBlank(packageName)) {
      throw new UnsupportedOperationException("packageName required");
    }

    if (StringUtils.isBlank(outputDir)) {
      throw new UnsupportedOperationException("outputDir required");
    }
  }
}
