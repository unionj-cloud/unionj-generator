package cloud.unionj.generator.backend.docparser.entity;

import cloud.unionj.generator.backend.springboot.Constants;
import cloud.unionj.generator.openapi3.dsl.SchemaHelper;
import cloud.unionj.generator.openapi3.model.Generic;
import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.openapi3.model.Schema;
import cloud.unionj.generator.openapi3.model.paths.Path;
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
 * cloud.unionj.generator.backend.docparser.entity
 * date 2020/12/21
 */
@Data
public class Backend {

  private static final String VO_PACKAGE_NAME = Constants.PACKAGE_NAME + ".vo";

  List<Vo> voList;
  List<Proto> protoList;

  @Data
  public static class PathWrapper {
    private Path path;
    private String endpoint;
    private String protoName;
  }

  public static Backend convert(Openapi3 openAPI) {
    Backend backend = new Backend();
    List<Vo> voList = new ArrayList<>();
    Map<String, Schema> schemas = openAPI.getComponents().getSchemas();
    for (Map.Entry<String, Schema> schemaEntry : schemas.entrySet()) {
      Vo vo = new Vo();
      Schema schema = schemaEntry.getValue();
      if (schema instanceof Generic || schema.isDummy()) {
        vo.setDummy(schema.getDummy());
        vo.setOutput(false);
      }
      String name = schemaEntry.getKey();
      List<String> genericPropertyList = new ArrayList<>();
      schema.getProperties().forEach((k, v) -> {
        if (v.equals(SchemaHelper.T) || v.equals(SchemaHelper.ListT) || v.equals(SchemaHelper.SetT)) {
          genericPropertyList.add(k);
        }
      });
      if (CollectionUtils.isNotEmpty(genericPropertyList)) {
        name += "<T>";
      } else {
        name = name.replaceAll(SchemaHelper.LEFT_ARROW, "<").replaceAll(SchemaHelper.RIGHT_ARROW, ">");
      }

      vo.setName(name);

      List<VoProperty> voPropertyList = new ArrayList<>();
      Map<String, Schema> properties = schema.getProperties();
      List<VoEnumType> enumTypeList = new ArrayList<>();
      List<String> importList = new ArrayList<>();
      for (Map.Entry<String, Schema> property : properties.entrySet()) {
        Schema value = property.getValue();
        VoProperty voProperty;
        if (CollectionUtils.isNotEmpty(value.getEnumValue())) {
          String type = StringUtils.capitalize(property.getKey()) + "Enum";
          voProperty = new VoProperty(property.getKey(), property.getKey(), type);

          List<VoEnum> voEnumList = value.getEnumValue().stream().map(item -> new VoEnum(item.toUpperCase(), item)).collect(Collectors.toList());
          VoEnumType voEnumType = new VoEnumType(voEnumList, type);
          enumTypeList.add(voEnumType);
        } else {
          if (StringUtils.isBlank(value.getType())) {
            String typeByRef = value.getTypeByRef(value.getRef());
            Schema typeByRefSchema = schemas.get(typeByRef);
            if (typeByRefSchema.isDummy()) {
              importList.add(typeByRefSchema.getDummy());
            } else if (typeByRefSchema instanceof Generic) {
              Generic genericValue = (Generic) typeByRefSchema;
              importList.addAll(genericValue.getDummies());
            }
          } else {
            if (value.isDummy()) {
              importList.add(value.getDummy());
            } else if (value instanceof Generic) {
              Generic genericValue = (Generic) value;
              importList.addAll(genericValue.getDummies());
            }
          }
          voProperty = new VoProperty(property.getKey(), property.getKey(), value);
        }
        voPropertyList.add(voProperty);
      }

      vo.setProperties(voPropertyList);
      vo.setEnumTypes(enumTypeList);
      vo.setImports(importList);

      voList.add(vo);
    }

    backend.setVoList(voList);

    Map<String, Path> paths = openAPI.getPaths();
    Map<String, List<PathWrapper>> pathWrapperMap = new HashMap<>();
    for (Map.Entry<String, Path> pathEntry : paths.entrySet()) {
      String key = pathEntry.getKey();
      String _key = StringUtils.stripStart(key, "/");
      if (StringUtils.isBlank(_key)) {
        continue;
      }
      String[] split = _key.split("/");
      if (ArrayUtils.isEmpty(split)) {
        continue;
      }
      PathWrapper wrapper = new PathWrapper();
      Path pathEntryValue = pathEntry.getValue();
      List<String> tags = pathEntryValue.getTags();
      String protoName;
      if (CollectionUtils.isNotEmpty(tags) && tags.size() > 1 && StringUtils.isNotBlank(tags.get(1))) {
        protoName = StringUtils.capitalize(tags.get(1)) + "Proto";
      } else {
        protoName = StringUtils.capitalize(split[0]) + "Proto";
      }
      wrapper.setProtoName(protoName);

      wrapper.setEndpoint(key);
      wrapper.setPath(pathEntry.getValue());

      List<PathWrapper> wrappers = pathWrapperMap.getOrDefault(protoName, Lists.newArrayList());
      wrappers.add(wrapper);
      pathWrapperMap.put(protoName, wrappers);
    }

    List<Proto> protoList = new ArrayList<>();
    for (Map.Entry<String, List<PathWrapper>> wrapperEntry : pathWrapperMap.entrySet()) {
      Proto proto = new Proto();
      proto.setName(wrapperEntry.getKey());

      List<ProtoRouter> routers = new ArrayList<>();
      List<PathWrapper> wrappers = wrapperEntry.getValue();
      for (PathWrapper wrapper : wrappers) {
        String key = wrapper.getEndpoint();
        if (StringUtils.isBlank(key)) {
          continue;
        }
        Path path = wrapper.getPath();
        if (path.getGet() != null) {
          routers.add(ProtoRouter.of(key, "get", path.getGet()));
        }
        if (path.getPost() != null) {
          routers.add(ProtoRouter.of(key, "post", path.getPost()));
        }
        if (path.getPut() != null) {
          routers.add(ProtoRouter.of(key, "put", path.getPut()));
        }
        if (path.getDelete() != null) {
          routers.add(ProtoRouter.of(key, "delete", path.getDelete()));
        }
      }

      proto.setRouters(routers);

      Set<String> dummies = voList.stream()
          .filter(vo -> vo != null)
          .filter(Vo::isDummy)
          .map(Vo::getDummy)
          .collect(Collectors.toSet());
      proto.setImports(Lists.newArrayList(dummies));
      protoList.add(proto);
    }

    backend.setProtoList(protoList);
    return backend;
  }
}
