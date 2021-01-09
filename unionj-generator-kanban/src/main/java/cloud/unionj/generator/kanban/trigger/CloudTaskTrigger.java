package cloud.unionj.generator.kanban.trigger;

import cloud.unionj.generator.kanban.handler.TaskHandler;
import cloud.unionj.generator.kanban.utils.ConsolePrint;
import cloud.unionj.generator.kanban.utils.DateTimeUtils;
import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.openapi3.model.paths.Operation;
import cloud.unionj.generator.openapi3.model.paths.Path;
import com.aliyuncs.devops_rdc.model.v20200303.CreateDevopsProjectTaskResponse;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * @version v0.1 cloud.unionj.generator
 * @author created by Johnny Ting
 * description: description
 * date: 2021-01-04 20:39
 **/
@Slf4j
public class CloudTaskTrigger implements Openapi3Trigger{
    /**
     * 调用云效api
     */
    @Override
    public void create(Openapi3 openapi3) {
        Map<String, Path> paths = openapi3.getPaths();
        for (Map.Entry<String, Path> entry : paths.entrySet()) {
            CompundOperation co = resolvefromOperation(entry.getKey(), entry.getValue());
            String note = co.getNote();
            Operation op = co.getOperation();
            if (TaskHandler.taskIsExistByName(op.getSummary())){
                continue;
            }
            String description = new StringBuilder(note)
                    .append(StringUtils.LF)
                    .append(StringUtils.CR)
                    .append(op.getDescription()).toString();
            CreateDevopsProjectTaskResponse response = TaskHandler.create(tr -> {
                tr.setContent(op.getSummary()); // 标题
                tr.setNote(description); // 备注
                tr.setPriority(0); // 优先级：0：普通（默认值）1：紧急2：非常紧急
                tr.setStartDate(DateTimeUtils.nowStringByUtc());
                tr.setDueDate(DateTimeUtils.afterWeekStringByUtc(1L));
            });
            ConsolePrint.pretty(response);
            if (!response.getSuccessful()){
                log.error("创建任务失败！！！");
            }
        }
    }

    public CompundOperation resolvefromOperation(String router, Path path){
        StringBuilder title = new StringBuilder();
        CompundOperation co = new CompundOperation();
        if (path.getGet() != null){
            title.append("GET: ");
            co.setOperation(path.getGet());
        }else if(path.getPost() != null){
            title.append("POST: ");
            co.setOperation(path.getPost());
        }else if(path.getDelete() != null){
            title.append("DELETE: ");
            co.setOperation(path.getDelete());
        }else {
            title.append("PUT: ");
            co.setOperation(path.getPut());
        }
        co.setNote(title.append(router).toString());
        return co;
    }

    @Data
    public class CompundOperation{
        private String note;
        private Operation operation;
    }

    public static void call(Openapi3 openapi3){
        new CloudTaskTrigger().create(openapi3);
    }
}
