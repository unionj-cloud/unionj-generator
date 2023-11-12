package cloud.unionj.generator;

import com.google.googlejavaformat.java.Formatter;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator
 * date 2020/11/21
 */
public abstract class DefaultGenerator implements Generator {

  protected boolean noDefaultComment;
  protected String parentArtifactId;
  protected String companyName;
  protected String baseName;
  protected String author;
  protected String createDate;
  protected String parentVersion;
  protected String year;
  protected String copyright;

  public DefaultGenerator(boolean noDefaultComment) {
    this.noDefaultComment = noDefaultComment;
  }

  public DefaultGenerator(boolean noDefaultComment, String parentArtifactId, String companyName, String baseName,
                          String author, String createDate, String parentVersion, String year, String copyright) {
    this.noDefaultComment = noDefaultComment;
    this.parentArtifactId = ObjectUtils.defaultIfNull(parentArtifactId, "");
    this.companyName = ObjectUtils.defaultIfNull(companyName, "");
    this.baseName = ObjectUtils.defaultIfNull(baseName, "");
    this.author = ObjectUtils.defaultIfNull(author, "");
    this.createDate = ObjectUtils.defaultIfNull(createDate, "");
    this.parentVersion = ObjectUtils.defaultIfNull(parentVersion, "");
    this.year = ObjectUtils.defaultIfNull(year, "");
    this.copyright = ObjectUtils.defaultIfNull(copyright, "");
  }

  @SneakyThrows
  public void doGenerate() {
    // 1. Configure FreeMarker
    //
    // You should do this ONLY ONCE, when your application starts,
    // then reuse the same Configuration object elsewhere.

    Configuration cfg = new Configuration(new Version(2, 3, 20));

    // Where do we load the templates from:
    cfg.setClassForTemplateLoading(DefaultGenerator.class, "/templates");

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
    Writer fileWriter = new FileWriter(getOutputFile());
    try {
      template.process(getInput(), fileWriter);
    } finally {
      fileWriter.close();
    }
  }

  @SneakyThrows
  @Override
  public String generate() {
    this.doGenerate();
    return getOutputFile();
  }

  @SneakyThrows
  @Override
  public String generateFormat() {
    this.doGenerate();
    Path path = Paths.get(getOutputFile());
    String sourceString = new String(Files.readAllBytes(path));
    String formattedSource = new Formatter().formatSource(sourceString);
    byte[] strToBytes = formattedSource.getBytes();
    Files.write(path, strToBytes);
    return getOutputFile();
  }

}
