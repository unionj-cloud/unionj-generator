package cloud.unionj.generator.service.docparser.entity;

import cloud.unionj.generator.openapi3.model.Schema;
import cloud.unionj.generator.openapi3.model.paths.*;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
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

  // TODO need implementation
  @Setter
  @Getter
  private List<BizProperty> headerParams;

  public static BizRouter of(String endpoint, String httpMethod, Operation operation) {
    BizRouter bizRouter = new BizRouter();
    bizRouter.endpoint = endpoint;
    bizRouter.httpMethod = httpMethod;
    if (StringUtils.isNotBlank(operation.getOperationId())) {
      bizRouter.name = operation.getOperationId();
    } else {
      bizRouter.setName();
    }

    boolean hasFormData = false;
    RequestBody requestBody = operation.getRequestBody();
    if (requestBody != null) {
      Content content = requestBody.getContent();
      if (content != null) {
        if (content.getApplicationJson() != null) {
          MediaType mediaType = content.getApplicationJson();
          if (mediaType.getSchema() != null) {
            BizProperty bizProperty = new BizProperty();
            bizProperty.setName("payload");
            bizProperty.setIn("requestBody");
            bizProperty.setType(mediaType.getSchema());
            bizRouter.setReqBody(bizProperty);
          }
        } else if (content.getApplicationOctetStream() != null) {
          MediaType mediaType = content.getApplicationOctetStream();
          if (mediaType.getSchema() != null) {
            BizProperty bizProperty = new BizProperty();
            bizProperty.setName("formData");
            bizProperty.setIn("requestBody");
            bizProperty.setType(TsTypeConstants.FORMDATA);
            bizRouter.setReqBody(bizProperty);
            hasFormData = true;
          }
        }
      }
    }

    Set<BizProperty> bizPropertySet = Sets.newLinkedHashSet();
    if (CollectionUtils.isNotEmpty(operation.getParameters())) {
      LinkedHashSet<BizProperty> bizProperties = operation.getParameters().stream()
          .map(para -> {
            BizProperty bizProperty = new BizProperty();
            bizProperty.setIn(para.getIn());
            bizProperty.setName(para.getName());
            bizProperty.setType(para.getSchema());
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
      if (!hasFormData) {
        bizRouter.setQueryParams(bizPropertyGroupByIn.get("query"));
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
            Schema schema = mediaType.getSchema();
            BizProperty bizProperty = new BizProperty();
            bizProperty.setName("data");
            bizProperty.setIn("responseBody");
            bizProperty.setType(schema);
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
