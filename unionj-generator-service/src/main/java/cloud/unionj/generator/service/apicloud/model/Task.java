package cloud.unionj.generator.service.apicloud.model;

import lombok.Data;

/**
 * @description: description
 * @author: Johnny Ting
 * @create: 2020-12-31 21:18
 **/
@Data
public class Task {
    private String orgId;
    private String projectId;
    private String scenarioFieldConfigId;
    private String executorId;
    private String taskFlowStatusId;
    private String startDate;
    private String dueDate;
    private String note;
    private int priority;
    private String visible;
    private String parentTaskId;
    private String sprintId;
    private String content;
    private String taskListId;
}
