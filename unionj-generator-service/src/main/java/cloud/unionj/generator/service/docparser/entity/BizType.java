package cloud.unionj.generator.service.docparser.entity;

import cloud.unionj.generator.openapi3.model.Schema;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.mock.docparser.entity
 * date 2020/11/18
 */

/**
 * ReqBody typescript interface representation
 */
@Data
public class BizType {

  private String name;
  private String doc;
  private List<BizProperty> properties;
  private List<BizEnumType> enumTypes;

  @SneakyThrows
  public String toCode() {
    Configuration cfg = new Configuration(new Version(2, 3, 20));
    // Some other recommended settings:
    cfg.setDefaultEncoding("UTF-8");
    cfg.setLocale(Locale.SIMPLIFIED_CHINESE);
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

    String tmpl = "{\n" +
        "<#list properties as property>\n" +
        "    <#list property.docs as doc>\n" +
        "    <#if doc??>// ${doc}</#if>\n" +
        "    </#list>\n" +
        "    ${property.name}<#if !property.required>?</#if>: ${property.type};\n" +
        "</#list>\n" +
        "}";

    Template template = new Template("BizType", new StringReader(tmpl), cfg);

    // write the freemarker output to a StringWriter
    StringWriter stringWriter = new StringWriter();
    template.process(this, stringWriter);

    // get the String from the StringWriter
    return stringWriter.toString();
  }

  public static BizType fromSchema(Schema schema) {
    return fromSchema(schema, null);
  }

  public static BizType fromSchema(Schema schema, String enumAs) {
    BizType bizType = new BizType();
    bizType.setDoc(schema.getDescription());
    List<BizProperty> bizPropertyList = new ArrayList<>();
    Map<String, Schema> properties = schema.getProperties();
    List<BizEnumType> enumTypeList = new ArrayList<>();
    List<String> required = schema.getRequired();
    for (Map.Entry<String, Schema> property : properties.entrySet()) {
      BizProperty bizProperty = new BizProperty();
      bizProperty.setDocs(new ArrayList<>());
      bizProperty.setName(property.getKey());
      Schema value = property.getValue();
      String desc = StringUtils.strip(value.getDescription());
      if (StringUtils.isNotBlank(desc)) {
        bizProperty.setDoc(desc);
        bizProperty.getDocs().add(desc);
      }
      if (CollectionUtils.isNotEmpty(value.getEnumValue())) {
        if (enumAs != null && enumAs.equals("doc")) {
          String enums = StringUtils.strip(StringUtils.join(value.getEnumValue(), ", "));
          if (StringUtils.isNotBlank(enums)) {
            bizProperty.setDoc(bizType.getDoc() + "\n" + enums);
            bizProperty.getDocs().add(enums);
          }
          bizProperty.setType(property.getValue());
        } else {
          String type = bizType.getName() + StringUtils.capitalize(property.getKey()) + "Enum";
          bizProperty.setType(type);
          List<BizEnum> voEnumList = value.getEnumValue().stream().map(item -> new BizEnum(item.toUpperCase(), item)).collect(Collectors.toList());
          BizEnumType voEnumType = new BizEnumType(voEnumList, type);
          enumTypeList.add(voEnumType);
        }
      } else {
        bizProperty.setType(property.getValue());
      }
      if (CollectionUtils.isNotEmpty(required)) {
        bizProperty.setRequired(required.contains(property.getKey()));
      }
      bizPropertyList.add(bizProperty);
    }
    bizType.setProperties(bizPropertyList);
    bizType.setEnumTypes(enumTypeList);
    return bizType;
  }

}
