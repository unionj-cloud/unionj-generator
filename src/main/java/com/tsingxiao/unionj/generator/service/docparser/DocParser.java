package com.tsingxiao.unionj.generator.service.docparser;

import com.google.common.collect.Lists;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizProperty;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizServer;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizService;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizType;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.parser.OpenAPIV3Parser;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.docparser
 * @date:2020/11/18
 */
@Data
public class DocParser {

  private static String[] redundantPorts = new String[]{":80", ":443"};
  private String doc;

  public DocParser(String doc) {
    this.doc = doc;
  }

  @Data
  public static class PathItemWrapper {
    private PathItem pathItem;
    private String endpoint;
    private String serviceName;
  }

  public BizServer parse() {
    BizServer bizServer = new BizServer();
    OpenAPI openAPI = new OpenAPIV3Parser().read(doc);
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
    bizServer.setName(host);

    List<BizType> types = new ArrayList<>();
    Map<String, Schema> schemas = openAPI.getComponents().getSchemas();
    for (Map.Entry<String, Schema> schemaEntry : schemas.entrySet()) {
      BizType bizType = new BizType();
      String key = schemaEntry.getKey();
      key = StringUtils.replace(StringUtils.replace(key, "«", ""), "»", "")
      bizType.setName(key);

      List<BizProperty> bizPropertyList = new ArrayList<>();

      Schema schema = schemaEntry.getValue();
      Map<String, Schema> properties = schema.getProperties();

      for (Map.Entry<String, Schema> property : properties.entrySet()) {
        BizProperty bizProperty = new BizProperty();
        bizProperty.setName(property.getKey());
        bizProperty.deepSetType(property.getValue());
        bizPropertyList.add(bizProperty);
      }

      bizType.setProperties(bizPropertyList);
      types.add(bizType);
    }

    bizServer.setTypes(types);

    Paths paths = openAPI.getPaths();
    Map<String, List<PathItemWrapper>> pathItemWrapperMap = new HashMap<>();
    for (Map.Entry<String, PathItem> pathItemEntry : paths.entrySet()) {
      String key = pathItemEntry.getKey();
      String _key = StringUtils.stripStart(key, "/");
      if (StringUtils.isBlank(_key)) {
        continue;
      }
      String[] split = _key.split("/");
      if (ArrayUtils.isEmpty(split)) {
        continue;
      }
      String serviceName = split[0];
      PathItemWrapper wrapper = new PathItemWrapper();
      key = StringUtils.replace(key, "{", "${");
      key = StringUtils.replace(key, "}", "");
      wrapper.setEndpoint(key);
      wrapper.setPathItem(pathItemEntry.getValue());
      wrapper.setServiceName(serviceName);

      List<PathItemWrapper> wrappers = pathItemWrapperMap.getOrDefault(serviceName, Lists.newArrayList());
      wrappers.add(wrapper);
      pathItemWrapperMap.put(serviceName, wrappers);
    }

    List<BizService> bizServiceList = new ArrayList<>();
    for (Map.Entry<String, List<PathItemWrapper>> wrapperEntry : pathItemWrapperMap.entrySet()) {
      BizService bizService = new BizService();
      bizService.setName(StringUtils.capitalize(wrapperEntry.getKey()) + "Service");

      // TODO
    }


    Map<String, List<ApiItem>> bizServerItemMap = bizServerItems.stream().collect(Collectors.groupingBy(ApiItem::getTag, Collectors.toList()));
    bizServer.setItems(bizServerItemMap);
    return bizServer;
  }


}
