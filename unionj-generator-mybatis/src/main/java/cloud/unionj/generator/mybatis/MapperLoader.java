package cloud.unionj.generator.mybatis;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.reflections.Reflections;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MapperLoader {

  List<MapperInfo> load(String prefix) {
    List<MapperInfo> mapperInfos = new ArrayList<>();
    Reflections reflections = new Reflections(prefix);
    Set<Class<?>> types = reflections
        .getTypesAnnotatedWith(Mapper.class);
    Iterator<Class<?>> iterator = types.iterator();
    while (iterator.hasNext()) {
      Class<?> next = iterator.next();
      MapperInfo mapperInfo = new MapperInfo();
      mapperInfo.setMapperName(StringUtils.substringAfterLast(next.getName(), "."));
      mapperInfo.setMapperPackage(StringUtils.substringBeforeLast(next.getName(), "."));
      List<String> imports = new ArrayList<>();
      imports.add(next.getName());
      List<MethodInfo> methodInfos = new ArrayList<>();
      Method[] methods = next.getMethods();
      for (int i = 0; i < methods.length; i++) {
        Method method = methods[i];
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setMethodName(method.getName());
        List<ParameterInfo> parameterInfos = new ArrayList<>();
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        for (int j = 0; j < genericParameterTypes.length; j++) {
          Type genericParameterType = genericParameterTypes[j];
          ParameterInfo parameterInfo = new ParameterInfo();
          List<String> argTypes = new ArrayList<>();
          if (genericParameterType instanceof ParameterizedTypeImpl) {
            ParameterizedTypeImpl parameterizedType = (ParameterizedTypeImpl) genericParameterType;
            Class<?> rawType = parameterizedType.getRawType();
            imports.add(rawType.getName());
            argTypes.add(StringUtils.substringAfterLast(rawType.getName(), "."));
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (int k = 0; k < actualTypeArguments.length; k++) {
              Class actualTypeArgument = (Class) actualTypeArguments[k];
              imports.add(actualTypeArgument.getName());
              argTypes.add(StringUtils.substringAfterLast(actualTypeArgument.getName(), "."));
            }
          } else if (genericParameterType instanceof Class) {
            Class genericParameterClazz = (Class) genericParameterType;
            imports.add(genericParameterClazz.getName());
            argTypes.add(StringUtils.substringAfterLast(genericParameterClazz.getName(), "."));
          } else {
            throw new RuntimeException("unknown parameter type");
          }
          parameterInfo.setTypes(argTypes);
          if (CollectionUtils.isNotEmpty(argTypes)) {
            String fullType = argTypes.get(0);
            if (argTypes.size() > 1) {
              fullType = fullType + "<";
              for (int k = 1; k < argTypes.size(); k++) {
                fullType = fullType + argTypes.get(k) + ",";
              }
              fullType = StringUtils.removeEnd(fullType, ",") + ">";
            }
            parameterInfo.setFullType(fullType);
          }
          parameterInfos.add(parameterInfo);
        }
        methodInfo.setParameters(parameterInfos);
        methodInfos.add(methodInfo);
      }
      mapperInfo.setImports(imports.stream().filter(i -> !i.contains("java.lang.")).collect(Collectors.toList()));
      mapperInfo.setMethods(methodInfos);
      mapperInfos.add(mapperInfo);
    }
    return mapperInfos;
  }
}
