package cloud.unionj.generator.service.apicloud.utils;

import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.service.apicloud.config.Aliyun;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.A;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * @description: description
 * @date: 2021-01-04 10:41
 **/
public class PropertiesToObject {

    @SneakyThrows
    public static <T> T parse(String path, Class<T> clazz) {
        Properties properties = new Properties();
        T object = clazz.newInstance();
        try (InputStream is = ClassLoader.getSystemResourceAsStream(path)) {
            properties.load(is);
        }
        Field[] fields = Class.forName(clazz.getName()).getDeclaredFields();
        for (Field field : fields) {
            if (java.lang.reflect.Modifier.isPublic(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            field.set(object, properties.getProperty(field.getName()));
        }
        return object;
    }
}
