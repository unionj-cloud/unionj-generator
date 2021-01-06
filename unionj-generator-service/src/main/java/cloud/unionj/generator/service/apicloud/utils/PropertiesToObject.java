package cloud.unionj.generator.service.apicloud.utils;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * description: description
 * date: 2021-01-04 10:41
 **/
public class PropertiesToObject {

    @SneakyThrows
    public static <T> T parse(String path, Class<T> clazz) {
        Properties properties = new Properties();
        T object = clazz.newInstance();
        try (InputStream is = new FileInputStream(path)) {
            properties.load(is);
        }
        objectSetter(object, properties, clazz);
        return object;
    }

    @SneakyThrows
    public static <T> T parse(InputStream is, Class<T> clazz) {
        Properties properties = new Properties();
        T object = clazz.newInstance();
        properties.load(is);
        objectSetter(object, properties, clazz);
        return object;
    }

    @SneakyThrows
    public static <T> T parse(File file, Class<T> clazz) {
        Properties properties = new Properties();
        T object = clazz.newInstance();
        try (InputStream is = new FileInputStream(file)) {
            properties.load(is);
        }
        objectSetter(object, properties, clazz);
        return object;
    }

    @SneakyThrows
    private static <T> T objectSetter(T object, Properties properties, Class<T> clazz) {
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
