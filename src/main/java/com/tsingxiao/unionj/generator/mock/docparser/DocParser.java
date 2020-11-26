package com.tsingxiao.unionj.generator.mock.docparser;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tsingxiao.unionj.generator.mock.docparser.entity.*;
import com.tsingxiao.unionj.generator.mock.schemafaker.DefaultSchemaFaker;
import com.tsingxiao.unionj.generator.mock.schemafaker.SchemaFaker;
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
import org.apache.commons.collections4.MapUtils;
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
public class DocParser {

  private static String[] redundantPorts = new String[]{":80", ":443"};
  private String doc;
  private SchemaFaker faker = new DefaultSchemaFaker();


  public DocParser(String doc) {
    this.doc = doc;
  }

  public DocParser(String doc, SchemaFaker faker) {
    this.doc = doc;
    this.faker = faker;
  }

  public Api parse() {
    Api api = new Api();
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
    api.setBaseUrl(host);

    List<ApiItem> apiItems = new ArrayList<>();
    Map<String, Schema> schemas = openAPI.getComponents().getSchemas();
    this.faker.setSchemas(schemas);
    Paths paths = openAPI.getPaths();
    for (Map.Entry<String, PathItem> pathItemEntry : paths.entrySet()) {
      String key = pathItemEntry.getKey();
      key = StringUtils.replace(key, "{", ":");
      key = StringUtils.replace(key, "}", "");
      PathItem pathItem = pathItemEntry.getValue();
      Set<ApiParam> apiParams = Sets.newHashSet();
      if (CollectionUtils.isNotEmpty(pathItem.getParameters())) {
        apiParams.addAll(pathItem.getParameters().stream()
            .map(para -> new ApiParam(para.getName(), para.getIn()))
            .collect(Collectors.toSet()));
      }
      Map<PathItem.HttpMethod, Operation> operationMap = pathItem.readOperationsMap();
      for (Map.Entry<PathItem.HttpMethod, Operation> entry : operationMap.entrySet()) {
        ApiItem apiItem = new ApiItem();
        apiItem.setEndpoint(key);
        apiItem.setMethod(entry.getKey().name().toLowerCase());
        if (CollectionUtils.isNotEmpty(entry.getValue().getTags())) {
          apiItem.setTag(entry.getValue().getTags().get(0));
        }
        Operation operation = entry.getValue();
        if (CollectionUtils.isNotEmpty(operation.getParameters())) {
          apiParams.addAll(operation.getParameters().stream()
              .map(para -> new ApiParam(para.getName(), para.getIn()))
              .collect(Collectors.toSet()));
        }

        if (CollectionUtils.isNotEmpty(apiParams)) {
          Map<String, List<String>> apiParamMap = apiParams.stream()
              .collect(Collectors.groupingBy(ApiParam::getIn, Collectors.mapping(ApiParam::getName, Collectors.toList())));
          apiItem.setPathParams(apiParamMap.get(ParameterIn.PATH.toString()));
          apiItem.setQueryParams(apiParamMap.get(ParameterIn.QUERY.toString()));
        }

        RequestBody requestBody = operation.getRequestBody();
        if (requestBody != null) {
          Content content = requestBody.getContent();
          if (content != null) {
            MediaType mediaType = content.get(ApiMediaType.JSON);
            if (mediaType != null) {
              if (MapUtils.isNotEmpty(mediaType.getSchema().getProperties())) {
                apiItem.setBodyParams(Lists.newArrayList(mediaType.getSchema().getProperties().keySet()));
              }
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
                apiItem.setResponse(this.faker.fakeObject(first.get().getSchema()));
              }
            }
          }
        }

        apiItems.add(apiItem);
      }
    }

    Map<String, List<ApiItem>> apiItemMap = apiItems.stream().collect(Collectors.groupingBy(ApiItem::getTag, Collectors.toList()));
    api.setItems(apiItemMap);
    return api;
  }


}
