package cloud.unionj.generator.service.apicloud.config;/**
 * @author dingxu
 * @version 1.0
 * date: 2021/1/5 09:49
 */

import java.io.File;
import java.io.InputStream;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * @description: description
 * @date: 2021-01-05 09:49
 **/
public class AliyunConfigLoad extends ConfigLoad{
    public static Aliyun AliyunConfig;

    public static void load(String path){ AliyunConfig = load(path, Aliyun.class); }

    public static void load(InputStream is){
        AliyunConfig = load(is, Aliyun.class);
    }

    public static void load(File file){ AliyunConfig = load(file, Aliyun.class); }
}
