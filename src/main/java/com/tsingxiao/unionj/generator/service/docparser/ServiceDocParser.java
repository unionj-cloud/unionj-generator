package com.tsingxiao.unionj.generator.service.docparser;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tsingxiao.unionj.generator.mock.docparser.entity.ApiMediaType;
import com.tsingxiao.unionj.generator.mock.docparser.entity.ApiStatusCode;
import com.tsingxiao.unionj.generator.service.docparser.entity.*;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.parser.OpenAPIV3Parser;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.docparser
 * @date:2020/11/18
 */
@Data
public class ServiceDocParser {

  private static String[] redundantPorts = new String[]{":80", ":443"};
  private String doc;

  public ServiceDocParser(String doc) {
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
      PathItemWrapper wrapper = new PathItemWrapper();
      String serviceName = StringUtils.capitalize(split[0]) + "Service";
      wrapper.setServiceName(serviceName);

      key = StringUtils.replace(key, "{", "${");
      key = StringUtils.replace(key, "}", "");
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
        PathItem pathItem = wrapper.getPathItem();
        Map<PathItem.HttpMethod, Operation> operationMap = pathItem.readOperationsMap();
        for (Map.Entry<PathItem.HttpMethod, Operation> entry : operationMap.entrySet()) {
          if (StringUtils.isBlank(wrapper.getEndpoint())) {
            continue;
          }
          BizRouter bizRouter = new BizRouter(wrapper.getEndpoint(), entry.getKey().name().toLowerCase());
          Set<BizProperty> bizPropertySet = Sets.newHashSet();
          if (CollectionUtils.isNotEmpty(pathItem.getParameters())) {
            bizPropertySet.addAll(pathItem.getParameters().stream()
                .map(para -> {
                  BizProperty bizProperty = new BizProperty();
                  bizProperty.setIn(para.getIn());
                  bizProperty.setName(para.getName());
                  bizProperty.setType(para.getSchema());
                  return bizProperty;
                })
                .collect(Collectors.toSet()));
          }
          Operation operation = entry.getValue();
          if (CollectionUtils.isNotEmpty(operation.getParameters())) {
            bizPropertySet.addAll(operation.getParameters().stream()
                .map(para -> {
                  BizProperty bizProperty = new BizProperty();
                  bizProperty.setIn(para.getIn());
                  bizProperty.setName(para.getName());
                  bizProperty.setType(para.getSchema());
                  return bizProperty;
                })
                .collect(Collectors.toSet()));
          }

          if (CollectionUtils.isNotEmpty(bizPropertySet)) {
            Map<String, List<BizProperty>> bizPropertyGroupByIn = bizPropertySet.stream()
                .collect(Collectors.groupingBy(BizProperty::getIn, Collectors.toList()));
            bizRouter.setPathParams(bizPropertyGroupByIn.get(ParameterIn.PATH.toString()));
            bizRouter.setQueryParams(bizPropertyGroupByIn.get(ParameterIn.QUERY.toString()));
          }

          RequestBody requestBody = operation.getRequestBody();
          if (requestBody != null) {
            Content content = requestBody.getContent();
            if (content != null) {
              MediaType mediaType = content.get(ApiMediaType.JSON);
              if (mediaType != null && mediaType.getSchema() != null) {
                BizProperty bizProperty = new BizProperty();
                bizProperty.setName("payload");
                bizProperty.setIn("requestBody");
                bizProperty.setType(mediaType.getSchema());
                bizRouter.setReqBody(bizProperty);
              }
            }
          }

          ApiResponses responses = operation.getResponses();
          if (responses != null) {
            ApiResponse okResponse = responses.get(ApiStatusCode.OK);
            if (okResponse != null) {
              Content content = okResponse.getContent();
              if (content != null) {
                Optional<MediaType> first = content.values().stream().findFirst();
                if (first.isPresent()) {
                  Schema schema = first.get().getSchema();
                  BizProperty bizProperty = new BizProperty();
                  bizProperty.setName("data");
                  bizProperty.setIn("responseBody");
                  bizProperty.setType(schema);
                  bizRouter.setRespData(bizProperty);
                }
              }
            }
          }

          bizRouters.add(bizRouter);
        }
      }

      bizService.setRouters(bizRouters);

      Set<String> serviceTypes = bizRouters.stream()
          .filter(bizRouter -> bizRouter.getReqBody() != null)
          .map(bizRouter -> {
            String type = bizRouter.getReqBody().getType();
            int index = type.indexOf("[]");
            if (index >= 0) {
              type = type.substring(0, index);
            }
            return type;
          }).collect(Collectors.toSet());

      serviceTypes.addAll(bizRouters.stream()
          .filter(bizRouter -> bizRouter.getRespData() != null && !bizRouter.getRespData().equals(TsTypeConstants.ANY))
          .map(bizRouter -> {
            String type = bizRouter.getRespData().getType();
            int index = type.indexOf("[]");
            if (index >= 0) {
              type = type.substring(0, index);
            }
            return type;
          }).collect(Collectors.toSet()));

      bizService.setTypes(Lists.newArrayList(serviceTypes));

      bizServiceList.add(bizService);
    }

    bizServer.setServices(bizServiceList);
    return bizServer;
  }
}
