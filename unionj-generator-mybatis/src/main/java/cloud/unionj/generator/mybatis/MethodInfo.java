package cloud.unionj.generator.mybatis;

import lombok.Data;

import java.util.List;

@Data
public class MethodInfo {
  private String methodName;
  private List<ParameterInfo> parameters;
}
