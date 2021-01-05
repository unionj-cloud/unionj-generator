package cloud.unionj.generator.service.apicloud.trigger;

import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.openapi3.model.paths.Operation;
import cloud.unionj.generator.openapi3.model.paths.Path;
import cloud.unionj.generator.service.apicloud.config.AliyunConfigLoad;
import cloud.unionj.generator.service.apicloud.handler.TaskHandler;
import cloud.unionj.generator.service.apicloud.utils.ConsolePrint;
import cloud.unionj.generator.service.apicloud.utils.DateTimeUtils;
import com.aliyuncs.devops_rdc.model.v20200303.CreateDevopsProjectTaskResponse;
import com.google.common.collect.Maps;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * @description: description
 * @date: 2021-01-04 20:39
 **/
public class CloudProjectTrigger implements Openapi3Trigger{
    /**
     * 调用云效api
     */
    @Override
    public void call(Openapi3 openapi3) {

    }


}
