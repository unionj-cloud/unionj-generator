package cloud.unionj.generator.backend.docparser;

import cloud.unionj.generator.backend.docparser.entity.Backend;
import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.openapi3.parser.Openapi3Parser;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.mock.docparser
 * @date:2020/11/18
 */
@Data
public class BackendDocParser {

  public static Backend parse(File doc) throws IOException {
    Openapi3 openAPI = new Openapi3Parser().parse(doc);
    return Backend.convert(openAPI);
  }

  public static Backend parse(URL doc) throws IOException {
    Openapi3 openAPI = new Openapi3Parser().parse(doc);
    return Backend.convert(openAPI);
  }

  public static Backend parse(InputStream doc) throws IOException {
    Openapi3 openAPI = new Openapi3Parser().parse(doc);
    return Backend.convert(openAPI);
  }

  public static Backend parse(Openapi3 openAPI) throws IOException {
    return Backend.convert(openAPI);
  }
}
