package cloud.unionj.generator.backend.apicloud.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

/**
 * @version v0.1 cloud.unionj.generator
 * @author created by Johnny Ting
 * description: description
 * date: 2021-01-05 10:17
 **/
public class ConsolePrint {

    @SneakyThrows
    public static void pretty(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(objectMapper.writeValueAsString(object));
    }
}
