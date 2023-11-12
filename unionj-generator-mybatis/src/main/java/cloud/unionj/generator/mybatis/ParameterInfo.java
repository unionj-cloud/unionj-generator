package cloud.unionj.generator.mybatis;

import lombok.Data;

import java.util.List;

@Data
public class ParameterInfo {
  private List<String> types;
  private String fullType;
}
