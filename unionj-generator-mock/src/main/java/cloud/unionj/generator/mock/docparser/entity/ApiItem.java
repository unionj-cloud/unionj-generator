package cloud.unionj.generator.mock.docparser.entity;

import cloud.unionj.generator.ApiItemVo;
import cloud.unionj.generator.mock.schemafaker.SchemaFaker;
import cloud.unionj.generator.openapi3.model.Schema;
import cloud.unionj.generator.openapi3.model.paths.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.NullNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.mock.docparser.entity
 * @date:2020/11/18
 */
@Data
public class ApiItem {

  protected static ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

  protected String endpoint;
  protected String method;
  protected String tag = "未分类";
  protected List<String> bodyParams;
  protected List<String> pathParams;
  protected List<String> queryParams;

  // TODO need implementation
  protected List<String> headerParams;

  protected JsonNode response;

  public static ApiItem of(String endpoint, String method, Operation operation, SchemaFaker faker) {
    ApiItem apiItem = new ApiItem();
    apiItem.setEndpoint(endpoint);
    apiItem.setMethod(method);
    if (CollectionUtils.isNotEmpty(operation.getTags())) {
      apiItem.setTag(operation.getTags().get(0));
    }
    Set<ApiParam> apiParams = Sets.newLinkedHashSet();
    if (CollectionUtils.isNotEmpty(operation.getParameters())) {
      LinkedHashSet<ApiParam> apiParamSet = operation.getParameters().stream()
          .map(para -> new ApiParam(para.getName(), para.getIn()))
          .collect(Collectors.toCollection(LinkedHashSet::new));
      if (apiParamSet != null) {
        apiParams.addAll(apiParamSet);
      }
    }

    if (CollectionUtils.isNotEmpty(apiParams)) {
      Map<String, List<String>> apiParamMap = apiParams.stream()
          .collect(Collectors.groupingBy(ApiParam::getIn, Collectors.mapping(ApiParam::getName, Collectors.toList())));
      apiItem.setPathParams(apiParamMap.get("path"));
      apiItem.setQueryParams(apiParamMap.get("query"));
    }

    RequestBody requestBody = operation.getRequestBody();
    if (requestBody != null) {
      Content content = requestBody.getContent();
      if (content != null) {
        MediaType mediaType = content.getApplicationJson();
        if (mediaType != null) {
          Schema schema = mediaType.getSchema();
          if (StringUtils.isNotBlank(schema.getRef())) {
            schema = faker.getSchemaByRef(schema.getRef());
          }
          if (MapUtils.isNotEmpty(schema.getProperties())) {
            apiItem.setBodyParams(Lists.newArrayList(schema.getProperties().keySet()));
          }
        }
      }
    }

    Responses responses = operation.getResponses();
    if (responses != null) {
      Response okResponse = responses.getResponse200();
      if (okResponse != null) {
        Content content = okResponse.getContent();
        if (content != null) {
          MediaType mediaType = content.getApplicationJson();
          if (mediaType != null) {
            apiItem.setResponse(faker.fakeObject(mediaType.getSchema()));
          }
        }
      }
    }

    return apiItem;
  }

  @SneakyThrows
  public ApiItemVo toApiItemVo() {
    ApiItemVo apiItemVo = mapper.readValue(mapper.writeValueAsString(this), ApiItemVo.class);
    if (apiItemVo.getResponse() instanceof NullNode) {
      return apiItemVo;
    }
    apiItemVo.setResponseStr(mapper.writeValueAsString(apiItemVo.getResponse()));
    apiItemVo.setResponse(null);
    return apiItemVo;
  }

}
