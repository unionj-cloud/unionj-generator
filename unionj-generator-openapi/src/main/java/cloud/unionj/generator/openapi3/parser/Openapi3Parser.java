package cloud.unionj.generator.openapi3.parser;

import cloud.unionj.generator.openapi3.model.Openapi3;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.parser
 *  date 2020/12/20
 */
public class Openapi3Parser {

  private static ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public Openapi3 parse(File doc) throws IOException {
    try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(doc))) {
      return parse(is);
    }
  }

  public Openapi3 parse(URL doc) throws IOException {
    try (BufferedInputStream is = new BufferedInputStream(doc.openStream())) {
      return parse(is);
    }
  }

  public Openapi3 parse(InputStream is) throws IOException {
    String doc = IOUtils.toString(is, StandardCharsets.UTF_8.name());
    JsonNode jsonNode = objectMapper.readValue(doc, JsonNode.class);
    Openapi3 openapi3;
    if (jsonNode.hasNonNull("openapi")) {
      openapi3 = objectMapper.readValue(doc, Openapi3.class);
    } else {
      String parsed = parse(doc);
      openapi3 = objectMapper.readValue(parsed, Openapi3.class);
    }
    return openapi3;
  }

  @SneakyThrows
  private String parse(String inputAsString) {
    SwaggerParseResult output = new OpenAPIParser().readContents(inputAsString, null, null);
    if (output == null) {
      throw new Exception("Invalid Swagger/OpenAPI specification!");
    }
    if (output.getOpenAPI() == null) {
      throw new Exception("Invalid Swagger/OpenAPI specification!");
    }
    return objectMapper.writeValueAsString(output.getOpenAPI());
  }
}
