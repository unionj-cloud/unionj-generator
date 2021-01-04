package cloud.unionj.generator.service;

import cloud.unionj.generator.service.apicloud.config.Aliyun;
import cloud.unionj.generator.service.apicloud.handler.TaskHandler;
import cloud.unionj.generator.service.apicloud.utils.PropertiesToObject;
import com.aliyuncs.devops_rdc.model.v20200303.CreateDevopsProjectTaskResponse;
import com.aliyuncs.devops_rdc.model.v20200303.ListDevopsScenarioFieldConfigResponse;
import lombok.SneakyThrows;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * @description: description
 * @date: 2021-01-04 09:50
 **/
public class TaskTest {

    @Test
    public void create() {

        CreateDevopsProjectTaskResponse response = TaskHandler.create(tr -> {
            tr.setNote("note"); // 备注
            tr.setPriority(1); // 优先级：0：普通（默认值）1：紧急2：非常紧急
            tr.setStartDate(LocalDateTime.now().toString());
            tr.setDueDate(LocalDateTime.now().plusWeeks(1L).toString());
            tr.setContent("test task"); // 任务内容
        });
        System.out.println(response);
    }

    @Test
    public void parse() {
        Aliyun instance = Aliyun.INSTANCE;
        ListDevopsScenarioFieldConfigResponse response = TaskHandler.fetchScenarioFieldConfig();
        System.out.println(response);
    }
}
