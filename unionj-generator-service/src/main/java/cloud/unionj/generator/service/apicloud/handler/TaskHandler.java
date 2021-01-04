package cloud.unionj.generator.service.apicloud.handler;

import cloud.unionj.generator.service.apicloud.config.Aliyun;
import cloud.unionj.generator.service.apicloud.constant.ScenarioFieldConfig;
import cloud.unionj.generator.service.apicloud.utils.AcsClient;
import com.aliyuncs.devops_rdc.model.v20200303.*;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import java.util.function.Consumer;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * @description: description
 * @date: 2021-01-04 09:40
 **/
public class TaskHandler {

    /**
     * 创建云效任务
     * @param consumer
     * @return
     */
    @SneakyThrows
    public static CreateDevopsProjectTaskResponse create(Consumer<CreateDevopsProjectTaskRequest> consumer){
        String taskId = fetchScenarioFieldConfigTaskId();
        String taskFlowId = fetchTaskFlowId();
        String taskListId = fetchTaskListId();
        CreateDevopsProjectTaskRequest createProjectTaskRequest = new CreateDevopsProjectTaskRequest();
        createProjectTaskRequest.setSysEndpoint(Aliyun.INSTANCE.getEndPoint());
        createProjectTaskRequest.setOrgId(Aliyun.INSTANCE.getOrgId());
        createProjectTaskRequest.setProjectId(Aliyun.INSTANCE.getProjectId());
        createProjectTaskRequest.setExecutorId(Aliyun.INSTANCE.getExecutorId());
        createProjectTaskRequest.setScenarioFieldConfigId(taskId);
        createProjectTaskRequest.setTaskFlowStatusId(taskFlowId);
        createProjectTaskRequest.setTaskListId(taskListId);
        consumer.accept(createProjectTaskRequest);
        return AcsClient.get().getAcsResponse(createProjectTaskRequest);
    }

    /**
     * 获取 ScenarioFieldConfig
     * @return
     */
    @SneakyThrows
    public static ListDevopsScenarioFieldConfigResponse fetchScenarioFieldConfig() {
        ListDevopsScenarioFieldConfigRequest listScenarioFieldConfigRequest = new ListDevopsScenarioFieldConfigRequest();
        listScenarioFieldConfigRequest.setOrgId(Aliyun.INSTANCE.getOrgId());
        listScenarioFieldConfigRequest.setProjectId(Aliyun.INSTANCE.getProjectId());
        listScenarioFieldConfigRequest.setSysEndpoint(Aliyun.INSTANCE.getEndPoint());
        return AcsClient.get().getAcsResponse(listScenarioFieldConfigRequest);
    }

    /**
     * 获取 ScenarioFieldConfig Task ID
     * @return
     */
    public static String fetchScenarioFieldConfigTaskId(){
        ListDevopsScenarioFieldConfigResponse res = fetchScenarioFieldConfig();

        if (res.getSuccessful()) {
            return res.getObject().stream()
                    .filter(ob -> StringUtils.equalsIgnoreCase(ob.getType(), ScenarioFieldConfig.TASK))
                    .map(ListDevopsScenarioFieldConfigResponse.ScenarioFieldConfig::getId)
                    .findFirst()
                    .get();
        }
        return null;
    }

    @SneakyThrows
    public static ListDevopsProjectTaskFlowResponse fetchTaskFlow(){
        ListDevopsProjectTaskFlowRequest listProjectTaskFlowRequest = new ListDevopsProjectTaskFlowRequest();
        listProjectTaskFlowRequest.setOrgId(Aliyun.INSTANCE.getOrgId());
        listProjectTaskFlowRequest.setProjectId(Aliyun.INSTANCE.getProjectId());
        listProjectTaskFlowRequest.setSysEndpoint(Aliyun.INSTANCE.getEndPoint());
        return AcsClient.get().getAcsResponse(listProjectTaskFlowRequest);
    }

    public static String fetchTaskFlowId(){
        ListDevopsProjectTaskFlowResponse response = fetchTaskFlow();
        if (response.getSuccessful()){
            return response.getObject().stream()
                    .filter(ob -> StringUtils.equalsIgnoreCase(ob.getType(), ScenarioFieldConfig.TASK))
                    .map(ListDevopsProjectTaskFlowResponse.Taskflow::getId)
                    .findFirst()
                    .get();

        }
        return null;
    }

    @SneakyThrows
    public static ListDevopsProjectTaskListResponse fetchTaskList(){
        ListDevopsProjectTaskListRequest list = new ListDevopsProjectTaskListRequest();
        list.setOrgId(Aliyun.INSTANCE.getOrgId());
        list.setProjectId(Aliyun.INSTANCE.getProjectId());
        list.setSysEndpoint(Aliyun.INSTANCE.getEndPoint());
        return AcsClient.get().getAcsResponse(list);
    }

    public static String fetchTaskListId(){
        ListDevopsProjectTaskListResponse response = fetchTaskList();
        if (response.getSuccessful()){
            return response.getObject().getResult().stream()
                    .findFirst().get().getId();

        }
        return null;
    }
}
