package cloud.unionj.generator.mock.docparser.entity;

import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.openapi3.model.Schema;
import cloud.unionj.generator.openapi3.model.paths.Path;
import cloud.unionj.generator.openapi3.model.servers.Server;
import cloud.unionj.generator.mock.schemafaker.DefaultSchemaFaker;
import cloud.unionj.generator.mock.schemafaker.SchemaFaker;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.mock.docparser.entity
 * @date:2020/11/18
 */
@Data
public class Api {

  private static String[] redundantPorts = new String[]{":80", ":443"};

  private String baseUrl;
  private Map<String, List<ApiItem>> items;

  public static Api convert(Openapi3 openAPI) {
    return convert(openAPI, new DefaultSchemaFaker());
  }

  public static Api convert(Openapi3 openAPI, SchemaFaker faker) {
    Api api = new Api();
    List<Server> servers = openAPI.getServers();
    Server defaultServer = null;
    if (CollectionUtils.isNotEmpty(servers)) {
      defaultServer = servers.get(0);
    }
    String host = defaultServer.getUrl();
    String redundantPort = "";
    for (String port : redundantPorts) {
      if (host.endsWith(port)) {
        redundantPort = port;
        break;
      }
    }
    host = StringUtils.removeEnd(host, redundantPort);
    api.setBaseUrl(host);

    List<ApiItem> apiItems = new ArrayList<>();
    Map<String, Schema> schemas = openAPI.getComponents().getSchemas();
    faker.setSchemas(schemas);
    Map<String, Path> paths = openAPI.getPaths();
    for (Map.Entry<String, Path> pathEntry : paths.entrySet()) {
      String key = pathEntry.getKey();
      key = StringUtils.replace(key, "{", ":");
      key = StringUtils.replace(key, "}", "");
      Path pathItem = pathEntry.getValue();
      if (pathItem.getGet() != null) {
        ApiItem apiItem = ApiItem.of(key, "get", pathItem.getGet(), faker);
        apiItems.add(apiItem);
      }
      if (pathItem.getPost() != null) {
        ApiItem apiItem = ApiItem.of(key, "post", pathItem.getPost(), faker);
        apiItems.add(apiItem);
      }
      if (pathItem.getPut() != null) {
        ApiItem apiItem = ApiItem.of(key, "put", pathItem.getPut(), faker);
        apiItems.add(apiItem);
      }
      if (pathItem.getDelete() != null) {
        ApiItem apiItem = ApiItem.of(key, "delete", pathItem.getDelete(), faker);
        apiItems.add(apiItem);
      }
    }

    Map<String, List<ApiItem>> apiItemMap = apiItems.stream().collect(Collectors.groupingBy(ApiItem::getTag, Collectors.toList()));
    api.setItems(apiItemMap);
    return api;
  }
}
