package cloud.unionj.generator.mybatis;

import java.util.List;

public class MainTest {
  public static void main(String[] args) {
    MapperLoader mapperLoader = new MapperLoader();
    List<MapperInfo> mapperInfos = mapperLoader.load("cloud.unionj.generator.mybatis.dao");
    System.out.println(mapperInfos);

    for (int i = 0; i < mapperInfos.size(); i++) {
      MapperInfo mapperInfo = mapperInfos.get(i);
      MapperTestJavaGenerator mapperTestJavaGenerator = new MapperTestJavaGenerator("/Users/wubin1989/workspace/cloud/unionj-generator/unionj-generator-mybatis", "cloud.unionj.generator.mybatis.Main", mapperInfo, false);
      String generate = mapperTestJavaGenerator.generate();
      System.out.println(generate);
    }
  }
}
