package cloud.unionj.generator.service.apicloud.utils;

import cloud.unionj.generator.service.apicloud.config.Aliyun;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * @description: description
 * @date: 2021-01-04 09:40
 **/
public class AcsClient {
    public static IAcsClient get(){
        DefaultProfile profile = DefaultProfile.getProfile(
                Aliyun.INSTANCE.getRegionId(),
                Aliyun.INSTANCE.getAccessKeyId(),
                Aliyun.INSTANCE.getSecret());
        IAcsClient client = new DefaultAcsClient(profile);
        return  client;
    }
}
