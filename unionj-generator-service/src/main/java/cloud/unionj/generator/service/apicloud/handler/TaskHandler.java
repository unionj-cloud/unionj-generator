package cloud.unionj.generator.service.apicloud.handler;

import cloud.unionj.generator.service.apicloud.config.AliyunConfigLoad;
import cloud.unionj.generator.service.apicloud.config.ConfigLoad;
import cloud.unionj.generator.service.apicloud.constant.ScenarioFieldConfig;
import cloud.unionj.generator.service.apicloud.constant.TaskFlowStatus;
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
public class TaskHandler extends AliyunConfigLoad{

    /**
     * 创建云效任务
     * @param consumer
     * @return
     */
    @SneakyThrows
    public static CreateDevopsProjectTaskResponse create(Consumer<CreateDevopsProjectTaskRequest> consumer){
        String taskId = fetchScenarioFieldConfigId(ScenarioFieldConfig.TASK);
        String taskFlowStatusId = fetchTaskFlowStatusId(TaskFlowStatus.START, fetchTaskFlowId(ScenarioFieldConfig.TASK));
        String taskListId = fetchTaskListId();
        CreateDevopsProjectTaskRequest createProjectTaskRequest = new CreateDevopsProjectTaskRequest();
        createProjectTaskRequest.setSysEndpoint(AliyunConfig.getEndPoint());
        createProjectTaskRequest.setOrgId(AliyunConfig.getOrgId());
        createProjectTaskRequest.setProjectId(AliyunConfig.getProjectId());
        createProjectTaskRequest.setExecutorId(AliyunConfig.getExecutorId());
        createProjectTaskRequest.setScenarioFieldConfigId(taskId);
        createProjectTaskRequest.setTaskFlowStatusId(taskFlowStatusId);
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
        listScenarioFieldConfigRequest.setOrgId(AliyunConfig.getOrgId());
        listScenarioFieldConfigRequest.setProjectId(AliyunConfig.getProjectId());
        listScenarioFieldConfigRequest.setSysEndpoint(AliyunConfig.getEndPoint());
        return AcsClient.get().getAcsResponse(listScenarioFieldConfigRequest);
    }

    /**
     * 获取 ScenarioFieldConfig Task ID
     * @return
     */
    public static String fetchScenarioFieldConfigId(String type){
        ListDevopsScenarioFieldConfigResponse res = fetchScenarioFieldConfig();

        if (res.getSuccessful()) {
            return res.getObject().stream()
                    .filter(ob -> StringUtils.equalsIgnoreCase(ob.getType(), type))
                    .map(ListDevopsScenarioFieldConfigResponse.ScenarioFieldConfig::getId)
                    .findFirst()
                    .get();
        }
        return null;
    }

    @SneakyThrows
    public static ListDevopsProjectTaskFlowResponse fetchTaskFlow(){
        ListDevopsProjectTaskFlowRequest listProjectTaskFlowRequest = new ListDevopsProjectTaskFlowRequest();
        listProjectTaskFlowRequest.setOrgId(AliyunConfig.getOrgId());
        listProjectTaskFlowRequest.setProjectId(AliyunConfig.getProjectId());
        listProjectTaskFlowRequest.setSysEndpoint(AliyunConfig.getEndPoint());
        return AcsClient.get().getAcsResponse(listProjectTaskFlowRequest);
    }

    public static String fetchTaskFlowId(String type){
        ListDevopsProjectTaskFlowResponse response = fetchTaskFlow();
        if (response.getSuccessful()){
            return response.getObject().stream()
                    .filter(ob -> StringUtils.equalsIgnoreCase(ob.getType(), type))
                    .map(ListDevopsProjectTaskFlowResponse.Taskflow::getId)
                    .findFirst()
                    .get();
        }
        return null;
    }

    @SneakyThrows
    public static ListDevopsProjectTaskFlowStatusResponse fetchTaskStatusFlow(String taskFlowId){
        ListDevopsProjectTaskFlowStatusRequest listProjectTaskFlowStatusRequest = new ListDevopsProjectTaskFlowStatusRequest();
        listProjectTaskFlowStatusRequest.setSysEndpoint(AliyunConfig.getEndPoint());
        listProjectTaskFlowStatusRequest.setOrgId(AliyunConfig.getOrgId());
        listProjectTaskFlowStatusRequest.setTaskFlowId(taskFlowId);
        return AcsClient.get().getAcsResponse(listProjectTaskFlowStatusRequest);
    }

    public static String fetchTaskFlowStatusId(String status, String taskFlowId){
        ListDevopsProjectTaskFlowStatusResponse response = fetchTaskStatusFlow(taskFlowId);
        if (response.getSuccessful()){
            return response.getObject().stream()
                    .filter(ob -> StringUtils.equalsIgnoreCase(ob.getKind(), status))
                    .map(ListDevopsProjectTaskFlowStatusResponse.TaskflowStatus::getId)
                    .findFirst()
                    .get();
        }
        return null;
    }

    @SneakyThrows
    public static ListDevopsProjectTaskListResponse fetchTaskList(){
        ListDevopsProjectTaskListRequest list = new ListDevopsProjectTaskListRequest();
        list.setOrgId(AliyunConfig.getOrgId());
        list.setProjectId(AliyunConfig.getProjectId());
        list.setSysEndpoint(AliyunConfig.getEndPoint());
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
