package cloud.unionj.generator.backend.springboot;

import org.junit.Test;

public class ControllerPomGeneratorTest {

  @Test
  public void test() {
    ControllerPomGenerator generator = new ControllerPomGenerator()
        .hasParent(true)
        .parentGroupId("com.github.myproject")
        .parentArtifactId("myproject")
        .parentVersion("1.0.0-SNAPSHOT")
        .groupIdAsParent()
        .artifactId("myproject-controller")
        .versionAsParent()
        .outputDirAsArtifactId()
        .protoGroupIdAsParent()
        .protoVersionAsParent()
        .protoArtifactId("myproject-proto")
        .serviceGroupIdAsParent()
        .serviceVersionAsParent()
        .serviceArtifactId("myproject-service");

    generator.generate();
  }

}
