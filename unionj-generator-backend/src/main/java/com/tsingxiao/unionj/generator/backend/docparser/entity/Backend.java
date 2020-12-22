package com.tsingxiao.unionj.generator.backend.docparser.entity;

import com.google.common.collect.Lists;
import com.tsingxiao.unionj.generator.openapi3.model.Openapi3;
import com.tsingxiao.unionj.generator.openapi3.model.Schema;
import com.tsingxiao.unionj.generator.openapi3.model.paths.Path;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.backend.docparser.entity
 * @date:2020/12/21
 */
@Data
public class Backend {

  List<Vo> voList;
  List<Proto> protoList;

  public static Backend convert(Openapi3 openAPI) {
    Backend backend = new Backend();
    List<Vo> voList = new ArrayList<>();
    Map<String, Schema> schemas = openAPI.getComponents().getSchemas();
    for (Map.Entry<String, Schema> schemaEntry : schemas.entrySet()) {
      Vo vo = new Vo();
      String key = schemaEntry.getKey();
      key = key.replaceAll("[^a-zA-Z]", "");
      vo.setName(key);

      List<VoProperty> voPropertyList = new ArrayList<>();

      Schema schema = schemaEntry.getValue();
      Map<String, Schema> properties = schema.getProperties();

      for (Map.Entry<String, Schema> property : properties.entrySet()) {
        VoProperty voProperty = new VoProperty(property.getKey(), property.getKey(), property.getValue());
        voPropertyList.add(voProperty);
      }

      vo.setProperties(voPropertyList);
      voList.add(vo);
    }

    backend.setVoList(voList);

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
              && !bizRouter.getReqBody().getType().equals(JavaTypeConstants.ANY)
              && !bizRouter.getReqBody().getType().equals(JavaTypeConstants.FORMDATA)
          )
          .map(bizRouter -> {
            String type = bizRouter.getReqBody().getType();
            int index = type.indexOf("[]");
            if (index >= 0) {
              type = type.substring(0, index);
            }
            if (JavaTypeConstants.values().contains(type)) {
              return null;
            }
            return type;
          }).filter(StringUtils::isNotBlank).collect(Collectors.toSet());

      serviceTypes.addAll(bizRouters.stream()
          .filter(bizRouter -> bizRouter.getRespData() != null && !bizRouter.getRespData().getType().equals(JavaTypeConstants.ANY))
          .map(bizRouter -> {
            String type = bizRouter.getRespData().getType();
            int index = type.indexOf("[]");
            if (index >= 0) {
              type = type.substring(0, index);
            }
            if (JavaTypeConstants.values().contains(type)) {
              return null;
            }
            return type;
          }).filter(StringUtils::isNotBlank).collect(Collectors.toSet()));

      bizService.setTypes(Lists.newArrayList(serviceTypes));

      bizServiceList.add(bizService);
    }

    backend.setServices(bizServiceList);
    return backend;
  }
}
