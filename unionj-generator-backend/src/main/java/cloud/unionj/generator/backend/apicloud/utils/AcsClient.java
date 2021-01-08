package cloud.unionj.generator.backend.apicloud.utils;

import cloud.unionj.generator.backend.apicloud.config.AliyunConfigLoad;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @version v0.1 cloud.unionj.generator
 * @author created by Johnny Ting
 * description: description
 * date: 2021-01-04 09:40
 **/
public class AcsClient extends AliyunConfigLoad {
    public static IAcsClient get(){
        DefaultProfile profile = DefaultProfile.getProfile(
                AliyunConfig.getRegionId(),
                AliyunConfig.getAccessKeyId(),
                AliyunConfig.getSecret());
        IAcsClient client = new DefaultAcsClient(profile);
        return  client;
    }
}
