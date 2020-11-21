package com.tsingxiao.unionj.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator
 * @date:2020/11/21
 */
public abstract class DefaultApiGenerator implements ApiGenerator {

  @Override
  public abstract Map<String, Object> getInput();

  @Override
  public abstract String getTemplate();

  @Override
  public abstract String getOutputFile();

  @SneakyThrows
  @Override
  public String generate() {
    // 1. Configure FreeMarker
    //
    // You should do this ONLY ONCE, when your application starts,
    // then reuse the same Configuration object elsewhere.

    Configuration cfg = new Configuration(new Version(2, 3, 20));

    // Where do we load the templates from:
    cfg.setClassForTemplateLoading(DefaultApiGenerator.class, "/templates");

    // Some other recommended settings:
    cfg.setDefaultEncoding("UTF-8");
    cfg.setLocale(Locale.SIMPLIFIED_CHINESE);
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

    // 2. Proccess template(s)
    //
    // You will do this for several times in typical applications.

    // 2.1. Prepare the template input:


    // 2.2. Get the template
    Template template = cfg.getTemplate(getTemplate());

    // 2.3. Generate the output
    Writer fileWriter = new FileWriter(new File(getOutputFile()));
    try {
      template.process(getInput(), fileWriter);
    } finally {
      fileWriter.close();
    }
    return getOutputFile();
  }
}
