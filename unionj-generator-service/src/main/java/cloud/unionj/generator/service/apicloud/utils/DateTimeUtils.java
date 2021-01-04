package cloud.unionj.generator.service.apicloud.utils;/**
 * @author dingxu
 * @version 1.0
 * date: 2021/1/4 23:09
 */

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
    private static final DateTimeFormatter utc = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private static final DateTimeFormatter local = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static String nowStringByUTC(){
        return LocalDateTime.now(ZoneId.of("UTC")).format(utc);
    }

    public static String afterWeekStringByUTC(Long l){
        return LocalDateTime.now(ZoneId.of("UTC")).plusWeeks(l).format(utc);
    }

    public static String nowStringByLocal(){
        return LocalDateTime.now().format(local);
    }
}
