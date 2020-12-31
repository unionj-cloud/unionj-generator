package cloud.unionj.generator.backend.docparser.entity;

import cloud.unionj.generator.backend.springboot.Constants;
import com.google.common.collect.Lists;
import cloud.unionj.generator.openapi3.dsl.SchemaHelper;
import cloud.unionj.generator.openapi3.model.Generic;
import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.openapi3.model.Schema;
import cloud.unionj.generator.openapi3.model.paths.Path;
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
 * @description: cloud.unionj.generator.backend.docparser.entity
 * @date:2020/12/21
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
      if (schema instanceof Generic) {
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
      for (Map.Entry<String, Schema> property : properties.entrySet()) {
        Schema value = property.getValue();
        VoProperty voProperty = null;
        if (CollectionUtils.isNotEmpty(value.getEnumValue())) {
          String type = StringUtils.capitalize(property.getKey()) + "Enum";
          voProperty = new VoProperty(property.getKey(), property.getKey(), type);

          List<VoEnum> voEnumList = value.getEnumValue().stream().map(item -> new VoEnum(item.toUpperCase(), item)).collect(Collectors.toList());
          VoEnumType voEnumType = new VoEnumType(voEnumList, type);
          enumTypeList.add(voEnumType);
        } else {
          voProperty = new VoProperty(property.getKey(), property.getKey(), value);
        }
        voPropertyList.add(voProperty);
      }

      vo.setProperties(voPropertyList);
      vo.setEnumTypes(enumTypeList);

      voList.add(vo);
    }

    backend.setVoList(voList);

    // TODO
//    List<String> voNameList = voList.stream().map(vo -> vo.getName()).collect(Collectors.toList());

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
      String protoName = StringUtils.capitalize(split[0]) + "Proto";
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

      // TODO
//      Set<String> protoTypes = routers.stream()
//          .filter(router -> router.getReqBody() != null)
//          .map(router -> {
//            String type = router.getReqBody().getType();
//            int index = type.indexOf("[]");
//            if (index >= 0) {
//              type = type.substring(0, index);
//            }
//            if (voNameList.contains(type)) {
//              return type;
//            }
//            return null;
//          }).filter(StringUtils::isNotBlank).collect(Collectors.toSet());
//
//      protoTypes.addAll(routers.stream()
//          .filter(router -> router.getRespData() != null)
//          .map(router -> {
//            String type = router.getRespData().getType();
//            int index = type.indexOf("[]");
//            if (index >= 0) {
//              type = type.substring(0, index);
//            }
//            if (voNameList.contains(type)) {
//              return type;
//            }
//            return null;
//          }).filter(StringUtils::isNotBlank).collect(Collectors.toSet()));

//      List<String> imports = protoTypes.stream().map(type -> VO_PACKAGE_NAME + "." + type).collect(Collectors.toList());
//      proto.setImports(imports);
      protoList.add(proto);
    }

    backend.setProtoList(protoList);
    return backend;
  }
}
