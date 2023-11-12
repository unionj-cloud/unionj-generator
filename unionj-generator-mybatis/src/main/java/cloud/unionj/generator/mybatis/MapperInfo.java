package cloud.unionj.generator.mybatis;

import lombok.Data;

import java.util.List;

@Data
public class MapperInfo {

  private String mapperName;
  private String mapperPackage;
  private List<MethodInfo> methods;
  private List<String> imports;

}
