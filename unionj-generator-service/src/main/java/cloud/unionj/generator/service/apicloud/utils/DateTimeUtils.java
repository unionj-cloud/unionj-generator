package cloud.unionj.generator.service.apicloud.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * @description: description
 * @date: 2021-01-04 23:09
 **/
public class DateTimeUtils {
    private static final DateTimeFormatter UTC = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private static final DateTimeFormatter LOCAL = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static String nowStringByUtc(){
        return LocalDateTime.now(ZoneId.of("UTC")).format(UTC);
    }

    public static String afterWeekStringByUtc(Long l){
        return LocalDateTime.now(ZoneId.of("UTC")).plusWeeks(l).format(UTC);
    }

    public static String nowStringByLocal(){
        return LocalDateTime.now().format(LOCAL);
    }
}
