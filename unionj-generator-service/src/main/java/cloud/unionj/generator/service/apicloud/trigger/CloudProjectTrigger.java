package cloud.unionj.generator.service.apicloud.trigger;

import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.openapi3.model.info.Info;
import cloud.unionj.generator.service.apicloud.config.AliyunConfigLoad;
import cloud.unionj.generator.service.apicloud.handler.ProjectHandler;
import com.aliyuncs.devops_rdc.model.v20200303.CreateDevopsProjectResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * description: description
 * date: 2021-01-04 20:39
 **/
@Slf4j
public class CloudProjectTrigger implements Openapi3Trigger{
    /**
     * 调用云效api 创建项目
     */
    @Override
    public void create(Openapi3 openapi3) {
        Info info = openapi3.getInfo();
        if (info == null){
            log.error("openapi3 info 为空，创建项目失败！！！");
            return;
        }
        CreateDevopsProjectResponse response = ProjectHandler.create(ob -> {
            ob.setName(info.getTitle()); // 项目名称
            ob.setDescription(info.getDescription()); // 项目描述
        });
        if(response.getSuccess()){
            String projectId = response.getObject();
            AliyunConfigLoad.AliyunConfig.setProjectId(projectId);
        }else {
            log.error("创建项目失败！！！");
        }
    }


}
