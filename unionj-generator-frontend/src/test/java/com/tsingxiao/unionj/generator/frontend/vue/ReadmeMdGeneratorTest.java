package com.tsingxiao.unionj.generator.frontend.vue;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.frontend.vue
 * @date:2020/11/26
 */
public class ReadmeMdGeneratorTest {

  @Test
  public void generate() {
    ReadmeMdGenerator readmeMdGenerator = new ReadmeMdGenerator("测试项目");
    String outputFile = readmeMdGenerator.generate();
    File file = new File(outputFile);
    Assert.assertTrue(file.exists());
  }
}
