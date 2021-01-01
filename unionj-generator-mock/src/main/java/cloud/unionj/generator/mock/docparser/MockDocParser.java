package cloud.unionj.generator.mock.docparser;

import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.openapi3.parser.Openapi3Parser;
import cloud.unionj.generator.mock.docparser.entity.Api;
import cloud.unionj.generator.mock.schemafaker.SchemaFaker;
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
public class MockDocParser {

  public static Api parse(File doc) throws IOException {
    Openapi3 openAPI = new Openapi3Parser().parse(doc);
    return Api.convert(openAPI);
  }

  public static Api parse(URL doc) throws IOException {
    Openapi3 openAPI = new Openapi3Parser().parse(doc);
    return Api.convert(openAPI);
  }

  public static Api parse(InputStream doc) throws IOException {
    Openapi3 openAPI = new Openapi3Parser().parse(doc);
    return Api.convert(openAPI);
  }

  public static Api parse(Openapi3 openAPI) throws IOException {
    return Api.convert(openAPI);
  }

  public static Api parse(File doc, SchemaFaker faker) throws IOException {
    Openapi3 openAPI = new Openapi3Parser().parse(doc);
    return Api.convert(openAPI, faker);
  }

  public static Api parse(URL doc, SchemaFaker faker) throws IOException {
    Openapi3 openAPI = new Openapi3Parser().parse(doc);
    return Api.convert(openAPI, faker);
  }

  public static Api parse(InputStream doc, SchemaFaker faker) throws IOException {
    Openapi3 openAPI = new Openapi3Parser().parse(doc);
    return Api.convert(openAPI, faker);
  }

  public static Api parse(Openapi3 openAPI, SchemaFaker faker) throws IOException {
    return Api.convert(openAPI, faker);
  }

}
