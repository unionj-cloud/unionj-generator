package cloud.unionj.generator.service;

import cloud.unionj.generator.service.apicloud.config.Aliyun;
import cloud.unionj.generator.service.apicloud.constant.ScenarioFieldConfig;
import cloud.unionj.generator.service.apicloud.handler.TaskHandler;
import cloud.unionj.generator.service.apicloud.utils.PropertiesToObject;
import com.aliyuncs.devops_rdc.model.v20200303.CreateDevopsProjectTaskResponse;
import com.aliyuncs.devops_rdc.model.v20200303.ListDevopsScenarioFieldConfigResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * @description: description
 * @date: 2021-01-04 09:50
 **/
public class TaskTest {

    @SneakyThrows
    @Test
    public void create() {
        CreateDevopsProjectTaskResponse response = TaskHandler.create(tr -> {
            tr.setContent("测试内容。。"); // 标题
            tr.setNote("云效任务测试"); // 备注
            tr.setPriority(0); // 优先级：0：普通（默认值）1：紧急2：非常紧急
        });
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        System.out.println(objectMapper.writeValueAsString(response));
    }

    @Test
    public void parse() {
        Aliyun instance = Aliyun.INSTANCE;
//        String id = TaskHandler.fetchScenarioFieldConfigId(ScenarioFieldConfig.TASK);
        System.out.println(instance);
    }
}
