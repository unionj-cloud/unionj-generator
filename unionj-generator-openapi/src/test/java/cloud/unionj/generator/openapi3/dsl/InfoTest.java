package cloud.unionj.generator.openapi3.dsl;

import cloud.unionj.generator.openapi3.model.Openapi3;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static cloud.unionj.generator.openapi3.dsl.info.Contact.contact;
import static cloud.unionj.generator.openapi3.dsl.info.Info.info;
import static cloud.unionj.generator.openapi3.dsl.info.Info.openapi3;
import static cloud.unionj.generator.openapi3.dsl.info.License.license;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.dsl
 * @date 2020/12/14
 */
public class InfoTest {

  @Test
  public void TestInfo1() throws JsonProcessingException {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("测试Info dsl");
        ib.description("test test");
        ib.version("v0.0.1");
        contact(cb -> {
          cb.email("328454505@qq.com");
        });
        license(lb -> {
          lb.url("www.unionj.com");
          lb.name("unionj");
        });
      });
    });
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    System.out.println(objectMapper.writeValueAsString(openapi3));
  }
}
