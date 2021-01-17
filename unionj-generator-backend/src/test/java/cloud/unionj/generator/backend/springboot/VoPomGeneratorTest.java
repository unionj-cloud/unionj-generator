package cloud.unionj.generator.backend.springboot;


import org.junit.Test;

/**
 * @author created by tqccc
 * @version v0.0.1
 * description: cloud.unionj.generator.backend.springboot
 * date:2021/1/17
 */
public class VoPomGeneratorTest {

  @Test
  public void test() {
    VoPomGenerator generator = VoPomGenerator.builder()
        .hasParent(true)
        .parentGroupId("com.github.myproject")
        .parentArtifactId("myproject")
        .parentVersion("1.0.0-SNAPSHOT")
        .groupIdAsParent()
        .artifactId("myproject-vo")
        .versionAsParent()
        .outputDirAsArtifactId()
        .build();


    generator.generate();
  }
}
