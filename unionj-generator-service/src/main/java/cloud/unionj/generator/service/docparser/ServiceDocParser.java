package cloud.unionj.generator.service.docparser;

import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.service.docparser.entity.BizServer;
import cloud.unionj.generator.openapi3.parser.Openapi3Parser;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.mock.docparser
 * @date 2020/11/18
 */
@Data
public class ServiceDocParser {

  public static BizServer parse(File doc) throws IOException {
    Openapi3 openAPI = new Openapi3Parser().parse(doc);
    return BizServer.convert(openAPI);
  }

  public static BizServer parse(URL doc) throws IOException {
    Openapi3 openAPI = new Openapi3Parser().parse(doc);
    return BizServer.convert(openAPI);
  }

  public static BizServer parse(InputStream doc) throws IOException {
    Openapi3 openAPI = new Openapi3Parser().parse(doc);
    return BizServer.convert(openAPI);
  }

  public static BizServer parse(Openapi3 openAPI) throws IOException {
    return BizServer.convert(openAPI);
  }
}
