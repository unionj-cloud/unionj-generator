package cloud.unionj.generator.service.docparser.entity;

import cloud.unionj.generator.openapi3.model.Schema;
import cloud.unionj.generator.openapi3.model.paths.*;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.mock.docparser.entity
 * date 2020/11/18
 */
public class BizRouter {
  @Getter
  private String name;
  @Getter
  private String endpoint;
  @Getter
  private String httpMethod;

  @Setter
  @Getter
  private BizProperty reqBody;
  @Setter
  @Getter
  private BizProperty respData = new BizProperty(TsTypeConstants.ANY);
  @Setter
  @Getter
  private List<BizProperty> pathParams;
  @Setter
  @Getter
  private List<BizProperty> queryParams;

  /**
   * pathParams + queryParams
   */
  @Setter
  @Getter
  private List<BizProperty> allParams;

  // TODO need implementation
  @Setter
  @Getter
  private List<BizProperty> headerParams;

  @Setter
  @Getter
  private List<String> docs;

  public static BizRouter of(String endpoint, String httpMethod, Operation operation) {
    BizRouter bizRouter = new BizRouter();
    bizRouter.endpoint = endpoint;
    bizRouter.httpMethod = httpMethod;
    List<String> strings = new ArrayList<>();
    if (StringUtils.isNotBlank(operation.getSummary())) {
      strings.addAll(Lists.newArrayList(operation.getSummary().split("[^\\S ]")));
    }
    if (StringUtils.isNotBlank(operation.getDescription())) {
      strings.addAll(Lists.newArrayList(operation.getDescription().split("[^\\S ]")));
    }
    bizRouter.docs = strings;
    if (StringUtils.isNotBlank(operation.getOperationId())) {
      bizRouter.name = operation.getOperationId();
    } else {
      bizRouter.setName();
    }

    RequestBody requestBody = operation.getRequestBody();
    if (requestBody != null) {
      Content content = requestBody.getContent();
      BizProperty bizProperty = new BizProperty();
      bizProperty.setDoc(requestBody.getDescription());
      if (content != null) {
        if (content.getApplicationJson() != null) {
          MediaType mediaType = content.getApplicationJson();
          if (mediaType.getSchema() != null) {
            bizProperty.setName("payload");
            bizProperty.setIn("requestBody");
            bizProperty.setType(mediaType.getSchema());
            bizRouter.setReqBody(bizProperty);
          }
        } else if (content.getApplicationOctetStream() != null) {
          MediaType mediaType = content.getApplicationOctetStream();
          if (mediaType.getSchema() != null) {
            bizProperty.setName("formData");
            bizProperty.setIn("requestBody");
            bizProperty.setType(TsTypeConstants.FORMDATA);
            bizRouter.setReqBody(bizProperty);
          }
        } else if (content.getMultipartFormData() != null) {
          MediaType formData = content.getMultipartFormData();
          if (formData.getSchema() != null) {
            bizProperty.setName("formData");
            bizProperty.setIn("requestBody");
            bizProperty.setType(TsTypeConstants.FORMDATA);
            bizRouter.setReqBody(bizProperty);
          }
        } else if (content.getTextPlain() != null) {
          MediaType mediaType = content.getTextPlain();
          if (mediaType.getSchema() != null) {
            bizProperty.setName("payload");
            bizProperty.setIn("requestBody");
            bizProperty.setType(mediaType.getSchema());
            bizRouter.setReqBody(bizProperty);
          }
        }
      }
    }

    Set<BizProperty> bizPropertySet = Sets.newLinkedHashSet();
    if (CollectionUtils.isNotEmpty(operation.getParameters())) {
      LinkedHashSet<BizProperty> bizProperties = operation.getParameters().stream()
          .map(para -> {
            BizProperty bizProperty = new BizProperty();
            bizProperty.setIn(para.getIn().toString());
            bizProperty.setName(para.getName());
            bizProperty.setType(para.getSchema());
            bizProperty.setRequired(para.isRequired());
            bizProperty.setDoc(para.getDescription());
            return bizProperty;
          })
          .collect(Collectors.toCollection(LinkedHashSet::new));
      if (bizProperties != null) {
        bizPropertySet.addAll(bizProperties);
      }
    }
    if (CollectionUtils.isNotEmpty(bizPropertySet)) {
      Map<String, List<BizProperty>> bizPropertyGroupByIn = bizPropertySet.stream()
          .collect(Collectors.groupingBy(BizProperty::getIn, Collectors.toList()));
      bizRouter.setPathParams(bizPropertyGroupByIn.get("path"));
      bizRouter.setQueryParams(bizPropertyGroupByIn.get("query"));

      List<BizProperty> bizProperties = Lists.newArrayList(bizPropertySet);
      List<BizProperty> requiredParams = bizProperties.stream().filter(BizProperty::isRequired).collect(Collectors.toList());
      List<BizProperty> optionalParams = bizProperties.stream().filter(bizProperty -> !bizProperty.isRequired()).collect(Collectors.toList());
      requiredParams.addAll(optionalParams);
      bizRouter.setAllParams(requiredParams);
    }

    Responses responses = operation.getResponses();
    if (responses != null) {
      Response okResponse = responses.getResponse200();
      if (okResponse != null) {
        Content content = okResponse.getContent();
        if (content != null) {
          MediaType mediaType = content.getApplicationJson();
          if (mediaType == null) {
            mediaType = content.getApplicationOctetStream();
          }
          if (mediaType == null) {
            mediaType = content.getTextPlain();
          }
          if (mediaType != null) {
            Schema schema = mediaType.getSchema();
            BizProperty bizProperty = new BizProperty();
            bizProperty.setName("data");
            bizProperty.setIn("responseBody");
            bizProperty.setType(schema);
            bizProperty.setDoc(okResponse.getDescription());
            bizRouter.setRespData(bizProperty);
          }
        }
      }
    }

    return bizRouter;
  }

  private void setName() {
    if (StringUtils.isBlank(this.getEndpoint()) || StringUtils.isBlank(this.getHttpMethod())) {
      return;
    }
    String endpoint = this.getEndpoint().replaceAll("[^a-zA-Z]", "_").toLowerCase();
    endpoint = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, endpoint);
    this.name = this.getHttpMethod().toLowerCase() + endpoint;
  }
}
