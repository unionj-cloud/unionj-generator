package cloud.unionj.generator.backend.docparser.entity;

import cloud.unionj.generator.openapi3.dsl.SchemaHelper;
import cloud.unionj.generator.openapi3.model.Schema;
import cloud.unionj.generator.openapi3.model.paths.*;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Sets;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.backend.docparser.entity
 * date 2020/12/21
 */
@Data
public class ProtoRouter {

  private String endpoint;
  private String name;
  private String httpMethod;
  private ProtoProperty reqBody;
  private ProtoProperty file;
  private ProtoProperty respData;
  private List<ProtoProperty> pathParams;
  private List<ProtoProperty> queryParams;

  // TODO
  private List<String> produces;
  // TODO
  private List<String> consumes;

  private ProtoRouter() {
  }

  public static ProtoRouter of(String endpoint, String httpMethod, Operation operation, Map<String, Schema> schemas) {
    ProtoRouter router = new ProtoRouter();
    router.endpoint = endpoint;
    router.httpMethod = httpMethod;
    if (StringUtils.isNotBlank(operation.getOperationId())) {
      router.name = operation.getOperationId();
    } else {
      if (StringUtils.isNotBlank(router.endpoint) && StringUtils.isNotBlank(router.httpMethod)) {
        String _endpoint = router.endpoint.replaceAll("[^a-zA-Z0-9_]", "_").toLowerCase();
        _endpoint = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, _endpoint);
        router.name = router.httpMethod.toLowerCase() + _endpoint;
      }
    }

    List<ProtoProperty> queryParams = new ArrayList<>();
    RequestBody requestBody = operation.getRequestBody();
    if (requestBody != null) {
      Content content = requestBody.getContent();
      if (content != null) {
        if (content.getApplicationJson() != null) {
          MediaType mediaType = content.getApplicationJson();
          if (mediaType.getSchema() != null) {
            router.setReqBody(new ProtoProperty.Builder(mediaType.getSchema()).name("body").required(requestBody.isRequired()).build());
          }
        } else if (content.getApplicationOctetStream() != null) {
          router.setFile(ProtoProperty.UPLOAD_FILE_BUILDER.required(requestBody.isRequired()).build());
        } else if (content.getMultipartFormData() != null) {
          MediaType formData = content.getMultipartFormData();
          Schema schema = formData.getSchema();
          if (schema.getRef() != null) {
            String typeByRef = schema.getTypeByRef(schema.getRef());
            schema = schemas.get(typeByRef);
          }
          List<String> required = schema.getRequired();
          schema.getProperties().forEach((k, v) -> {
            if (k.contains("file")) {
              if (v.getType().equals("array")) {
                router.setFile(ProtoProperty.UPLOAD_FILES_BUILDER.required(required.contains(k)).build());
              } else {
                router.setFile(ProtoProperty.UPLOAD_FILE_BUILDER.required(required.contains(k)).build());
              }
            } else {
              if (v.getType().equals("array") && v.getItems().equals(SchemaHelper.file)) {
                router.setFile(new ProtoProperty.Builder("MultipartFile[]").name(k).required(required.contains(k)).build());
              } else if (v.equals(SchemaHelper.file)) {
                router.setFile(new ProtoProperty.Builder("MultipartFile").name(k).required(required.contains(k)).build());
              } else {
                queryParams.add(new ProtoProperty.Builder(v)
                    .name(k)
                    .in("formData")
                    .required(required.contains(k))
                    .defaultValue(Optional.ofNullable(v.getDefaultValue()).orElse("").toString())
                    .build());
              }
            }
          });
        } else if (content.getTextPlain() != null) {
          MediaType mediaType = content.getTextPlain();
          if (mediaType.getSchema() != null) {
            router.setReqBody(new ProtoProperty.Builder(mediaType.getSchema()).name("body").required(requestBody.isRequired()).build());
          }
        }
      }
    }

    List<Parameter> parameters = operation.getParameters();
    Set<ProtoProperty> protoPropertySet = Sets.newLinkedHashSet();
    if (CollectionUtils.isNotEmpty(parameters)) {
      LinkedHashSet<ProtoProperty> protoProperties = parameters.stream()
          .map(para -> {
            ProtoProperty property;
            if (para.getIn() == Parameter.InEnum.QUERY) {
              property = new ProtoProperty.Builder(para.getSchema())
                  .name(para.getName())
                  .in(para.getIn().toString())
                  .required(para.isRequired())
                  .defaultValue(Optional.ofNullable(para.getSchema().getDefaultValue()).orElse("").toString())
                  .build();
            } else {
              property = new ProtoProperty.Builder(para.getSchema()).name(para.getName()).in(para.getIn().toString()).required(para.isRequired()).build();
            }
            return property;
          })
          .collect(Collectors.toCollection(LinkedHashSet::new));
      if (protoProperties != null) {
        protoPropertySet.addAll(protoProperties);
      }
    }
    if (CollectionUtils.isNotEmpty(protoPropertySet)) {
      Map<String, List<ProtoProperty>> protoPropertyMap = protoPropertySet.stream().collect(Collectors.groupingBy(ProtoProperty::getIn, Collectors.toList()));
      router.setPathParams(protoPropertyMap.get("path"));
      List<ProtoProperty> query = protoPropertyMap.get("query");
      if (query != null) {
        queryParams.addAll(query);
      }
    }
    router.setQueryParams(queryParams);

    Responses responses = operation.getResponses();
    if (responses != null) {
      Response okResponse = responses.getResponse200();
      if (okResponse != null) {
        Content content = okResponse.getContent();
        if (content != null) {
          if (content.getApplicationJson() != null) {
            MediaType mediaType = content.getApplicationJson();
            router.setRespData(new ProtoProperty.Builder(mediaType.getSchema()).build());
          } else if (content.getApplicationOctetStream() != null) {
            router.setRespData(ProtoProperty.STREAM);
          } else if (content.getTextPlain() != null) {
            MediaType mediaType = content.getTextPlain();
            router.setRespData(new ProtoProperty.Builder(mediaType.getSchema()).build());
          } else {
            router.setRespData(new ProtoProperty.Builder("void").build());
          }
        } else {
          router.setRespData(new ProtoProperty.Builder("void").build());
        }
      } else {
        router.setRespData(new ProtoProperty.Builder("void").build());
      }
    }

    return router;
  }

  public static class Builder {
    private String endpoint;
    private String name;
    private String httpMethod;
    private ProtoProperty reqBody;
    private ProtoProperty file;
    private ProtoProperty respData;
    private List<ProtoProperty> pathParams;
    private List<ProtoProperty> queryParams;

    public Builder(String endpoint, String name, String httpMethod) {
      this.endpoint = endpoint;
      this.httpMethod = httpMethod;
      if (StringUtils.isNotBlank(name)) {
        this.name = name;
      } else {
        if (StringUtils.isNotBlank(this.endpoint) && StringUtils.isNotBlank(this.httpMethod)) {
          String _endpoint = this.endpoint.replaceAll("[^a-zA-Z0-9_]", "_").toLowerCase();
          _endpoint = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, _endpoint);
          this.name = this.httpMethod.toLowerCase() + _endpoint;
        }
      }
    }

    public Builder reqBody(ProtoProperty reqBody) {
      this.reqBody = reqBody;
      return this;
    }

    public Builder file(ProtoProperty file) {
      this.file = file;
      return this;
    }

    public Builder respData(ProtoProperty respData) {
      this.respData = respData;
      return this;
    }

    public Builder pathParams(List<ProtoProperty> pathParams) {
      this.pathParams = pathParams;
      return this;
    }

    public Builder queryParams(List<ProtoProperty> queryParams) {
      this.queryParams = queryParams;
      return this;
    }

    public ProtoRouter build() {
      ProtoRouter router = new ProtoRouter();
      router.endpoint = this.endpoint;
      router.name = this.name;
      router.httpMethod = this.httpMethod;
      router.reqBody = this.reqBody;
      router.file = this.file;
      router.respData = this.respData;
      router.pathParams = this.pathParams;
      router.queryParams = this.queryParams;
      return router;
    }
  }
}
