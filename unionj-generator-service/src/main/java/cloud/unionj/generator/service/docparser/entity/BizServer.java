package cloud.unionj.generator.service.docparser.entity;

import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.openapi3.model.Schema;
import cloud.unionj.generator.openapi3.model.paths.Path;
import cloud.unionj.generator.openapi3.model.servers.Server;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.mock.docparser.entity
 * date 2020/11/18
 */
@Data
public class BizServer {

  private static String[] redundantPorts = new String[]{":80", ":443"};

  @Data
  public static class PathItemWrapper {
    private Path pathItem;
    private String endpoint;
    private String service;
    private String module;
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
    String host = "";
    if (defaultServer != null) {
      host = defaultServer.getUrl();
    }
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
      String key = schemaEntry.getKey();
      key = key.replaceAll("[^a-zA-Z0-9_]", "");
      BizType bizType = BizType.fromSchema(schemaEntry.getValue(), key);
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
      Path pathEntryValue = pathItemEntry.getValue();
      List<String> tags = pathEntryValue.returnTags();
      String serviceName;

      /**
       * Use second tag as Service class name if it exists, otherwise use first part of the path splitted by back slash.
       */
      if (CollectionUtils.isNotEmpty(tags) && tags.size() > 1 && StringUtils.isNotBlank(tags.get(1))) {
        serviceName = StringUtils.capitalize(tags.get(1).replaceAll("[^a-zA-Z0-9_]", "")) + "Service";
      } else {
        serviceName = StringUtils.capitalize(split[0].replaceAll("[^a-zA-Z0-9_]", "")) + "Service";
      }
      wrapper.setService(serviceName);
      String moduleName;
      if (CollectionUtils.isNotEmpty(tags)) {
        moduleName = StringUtils.capitalize(tags.get(0));
      } else {
        moduleName = StringUtils.capitalize(split[0]);
      }
      wrapper.setModule(moduleName);

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
      List<PathItemWrapper> wrappers = wrapperEntry.getValue();
      if (CollectionUtils.isEmpty(wrappers)) {
        continue;
      }
      bizService.setModule(wrappers.get(0).getModule());

      List<BizRouter> bizRouters = new ArrayList<>();
      for (PathItemWrapper wrapper : wrappers) {
        if (StringUtils.isBlank(wrapper.getEndpoint())) {
          continue;
        }
        Path pathItem = wrapper.getPathItem();
        if (CollectionUtils.isNotEmpty(pathItem.getParameters())) {
          System.out.println("not support parameters property of Path object yet");
          ;
        }
        if (pathItem.getGet() != null) {
          BizRouter bizRouter = BizRouter.of(wrapper.getEndpoint(), "get", pathItem.getGet(), openAPI.getComponents());
          bizRouters.add(bizRouter);
        }
        if (pathItem.getPost() != null) {
          BizRouter bizRouter = BizRouter.of(wrapper.getEndpoint(), "post", pathItem.getPost(), openAPI.getComponents());
          bizRouters.add(bizRouter);
        }
        if (pathItem.getPut() != null) {
          BizRouter bizRouter = BizRouter.of(wrapper.getEndpoint(), "put", pathItem.getPut(), openAPI.getComponents());
          bizRouters.add(bizRouter);
        }
        if (pathItem.getDelete() != null) {
          BizRouter bizRouter = BizRouter.of(wrapper.getEndpoint(), "delete", pathItem.getDelete(), openAPI.getComponents());
          bizRouters.add(bizRouter);
        }
      }

      bizService.setRouters(bizRouters);

      Set<String> serviceTypes = bizRouters.stream()
          .filter(bizRouter -> bizRouter.getReqBody() != null
              && !bizRouter.getReqBody().getType().equals(TsTypeConstants.ANY)
              && !bizRouter.getReqBody().getType().equals(TsTypeConstants.FORMDATA)
              && !bizRouter.getReqBody().getType().equals(TsTypeConstants.BLOB)
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
          }).filter(StringUtils::isNotBlank).collect(Collectors.toCollection(LinkedHashSet::new));

      LinkedHashSet<String> collect = bizRouters.stream()
          .filter(bizRouter -> bizRouter.getRespData() != null
              && !bizRouter.getRespData().getType().equals(TsTypeConstants.ANY)
              && !bizRouter.getRespData().getType().equals(TsTypeConstants.BLOB)
          )
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
          }).filter(StringUtils::isNotBlank).collect(Collectors.toCollection(LinkedHashSet::new));
      if (collect != null) {
        serviceTypes.addAll(collect);
      }

      bizService.setTypes(serviceTypes.stream().filter(t -> !t.startsWith("{")).collect(Collectors.toList()));

      bizServiceList.add(bizService);
    }

    bizServer.setServices(bizServiceList);
    return bizServer;
  }
}
