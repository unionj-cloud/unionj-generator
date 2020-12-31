package cloud.unionj.generator.service.docparser.entity;

import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.openapi3.model.paths.Path;
import cloud.unionj.generator.openapi3.model.servers.Server;
import com.google.common.collect.Lists;
import cloud.unionj.generator.openapi3.model.Schema;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.mock.docparser.entity
 * @date:2020/11/18
 */
@Data
public class BizServer {

  private static String[] redundantPorts = new String[]{":80", ":443"};

  @Data
  public static class PathItemWrapper {
    private Path pathItem;
    private String endpoint;
    private String serviceName;
  }

  private String name;
  private List<BizService> services;
  private List<BizType> types;

  public static BizServer convert(Openapi3 openAPI) {
    BizServer bizServer = new BizServer();
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
      key = key.replaceAll("[^a-zA-Z]", "");
      bizType.setName(key);

      List<BizProperty> bizPropertyList = new ArrayList<>();

      Schema schema = schemaEntry.getValue();
      Map<String, Schema> properties = schema.getProperties();

      for (Map.Entry<String, Schema> property : properties.entrySet()) {
        BizProperty bizProperty = new BizProperty();
        bizProperty.setName(property.getKey());
        bizProperty.setType(property.getValue());
        bizPropertyList.add(bizProperty);
      }

      bizType.setProperties(bizPropertyList);
      types.add(bizType);
    }

    bizServer.setTypes(types);

    Map<String, Path> paths = openAPI.getPaths();
    Map<String, List<PathItemWrapper>> pathItemWrapperMap = new HashMap<>();
    for (Map.Entry<String, Path> pathItemEntry : paths.entrySet()) {
      String key = pathItemEntry.getKey();
      String _key = StringUtils.stripStart(key, "/");
      if (StringUtils.isBlank(_key)) {
        continue;
      }
      String[] split = _key.split("/");
      if (ArrayUtils.isEmpty(split)) {
        continue;
      }
      PathItemWrapper wrapper = new PathItemWrapper();
      String serviceName = StringUtils.capitalize(split[0]) + "Service";
      wrapper.setServiceName(serviceName);

      key = StringUtils.replace(key, "{", "${");
      wrapper.setEndpoint(key);
      wrapper.setPathItem(pathItemEntry.getValue());

      List<PathItemWrapper> wrappers = pathItemWrapperMap.getOrDefault(serviceName, Lists.newArrayList());
      wrappers.add(wrapper);
      pathItemWrapperMap.put(serviceName, wrappers);
    }

    List<BizService> bizServiceList = new ArrayList<>();
    for (Map.Entry<String, List<PathItemWrapper>> wrapperEntry : pathItemWrapperMap.entrySet()) {
      BizService bizService = new BizService();
      bizService.setName(wrapperEntry.getKey());

      List<BizRouter> bizRouters = new ArrayList<>();
      List<PathItemWrapper> wrappers = wrapperEntry.getValue();
      for (PathItemWrapper wrapper : wrappers) {
        if (StringUtils.isBlank(wrapper.getEndpoint())) {
          continue;
        }
        Path pathItem = wrapper.getPathItem();
        if (pathItem.getGet() != null) {
          BizRouter bizRouter = BizRouter.of(wrapper.getEndpoint(), "get", pathItem.getGet());
          bizRouters.add(bizRouter);
        }
        if (pathItem.getPost() != null) {
          BizRouter bizRouter = BizRouter.of(wrapper.getEndpoint(), "post", pathItem.getPost());
          bizRouters.add(bizRouter);
        }
        if (pathItem.getPut() != null) {
          BizRouter bizRouter = BizRouter.of(wrapper.getEndpoint(), "put", pathItem.getPut());
          bizRouters.add(bizRouter);
        }
        if (pathItem.getDelete() != null) {
          BizRouter bizRouter = BizRouter.of(wrapper.getEndpoint(), "delete", pathItem.getDelete());
          bizRouters.add(bizRouter);
        }
      }

      bizService.setRouters(bizRouters);

      Set<String> serviceTypes = bizRouters.stream()
          .filter(bizRouter -> bizRouter.getReqBody() != null
              && !bizRouter.getReqBody().getType().equals(TsTypeConstants.ANY)
              && !bizRouter.getReqBody().getType().equals(TsTypeConstants.FORMDATA)
          )
          .map(bizRouter -> {
            String type = bizRouter.getReqBody().getType();
            int index = type.indexOf("[]");
            if (index >= 0) {
              type = type.substring(0, index);
            }
            if (TsTypeConstants.values().contains(type)) {
              return null;
            }
            return type;
          }).filter(StringUtils::isNotBlank).collect(Collectors.toSet());

      serviceTypes.addAll(bizRouters.stream()
          .filter(bizRouter -> bizRouter.getRespData() != null && !bizRouter.getRespData().getType().equals(TsTypeConstants.ANY))
          .map(bizRouter -> {
            String type = bizRouter.getRespData().getType();
            int index = type.indexOf("[]");
            if (index >= 0) {
              type = type.substring(0, index);
            }
            if (TsTypeConstants.values().contains(type)) {
              return null;
            }
            return type;
          }).filter(StringUtils::isNotBlank).collect(Collectors.toSet()));

      bizService.setTypes(Lists.newArrayList(serviceTypes));

      bizServiceList.add(bizService);
    }

    bizServer.setServices(bizServiceList);
    return bizServer;
  }
}
