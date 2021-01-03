package cloud.unionj.generator.service.apicloud.utils;

import cloud.unionj.generator.service.apicloud.constant.Aliyun;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @description: description
 * @author: Johnny Ting
 * @create: 2021-01-01 13:39
 **/
public class AcsClient {

    public static IAcsClient get(){
        DefaultProfile profile = DefaultProfile.getProfile(
                Aliyun.REGION_ID,
                Aliyun.ACCESS_KEY_ID,
                Aliyun.SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        return  client;
    }
}
