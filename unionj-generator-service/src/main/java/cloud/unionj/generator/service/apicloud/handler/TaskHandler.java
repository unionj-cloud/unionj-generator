package cloud.unionj.generator.service.apicloud.handler;

import cloud.unionj.generator.service.apicloud.constant.Aliyun;
import cloud.unionj.generator.service.apicloud.model.Task;
import cloud.unionj.generator.service.apicloud.utils.AcsClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.devops_rdc.model.v20200303.CreateDevopsProjectTaskRequest;
import com.aliyuncs.devops_rdc.model.v20200303.CreateDevopsProjectTaskResponse;
import com.aliyuncs.profile.DefaultProfile;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.function.Consumer;

/**
 * @description: description
 * @author: Johnny Ting
 * @create: 2020-12-31 22:35
 **/
public class TaskHandler {

    @SneakyThrows
    public static CreateDevopsProjectTaskResponse create(Consumer<CreateDevopsProjectTaskRequest> consumer){
        CreateDevopsProjectTaskRequest createProjectTaskRequest = new CreateDevopsProjectTaskRequest();
        consumer.accept(createProjectTaskRequest);
//        createProjectTaskRequest.setSysEndpoint(Aliyun.END_POINT);
//        createProjectTaskRequest.setOrgId("<orgId>");
//        createProjectTaskRequest.setProjectId("<projectId>");
//        createProjectTaskRequest.setScenarioFieldConfigId("<scenarioFieldConfigId>");
//        createProjectTaskRequest.setTaskFlowStatusId("<taskFlowStatusId>");
//        createProjectTaskRequest.setContent("创建任务API-TEST");
//        createProjectTaskRequest.setExecutorId("<executorId>");
//        createProjectTaskRequest.setTaskListId("<taskListId>");
//        createProjectTaskRequest.setStartDate(LocalDateTime.now().toString());
//        createProjectTaskRequest.setDueDate(LocalDateTime.now().plusWeeks(1).toString());
        return AcsClient.get().getAcsResponse(createProjectTaskRequest);
    }
}
