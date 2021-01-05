package cloud.unionj.generator.service.apicloud.trigger;/**
 * @author dingxu
 * @version 1.0
 * date: 2021/1/4 20:39
 */

import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.openapi3.model.paths.Operation;
import cloud.unionj.generator.openapi3.model.paths.Path;
import cloud.unionj.generator.service.apicloud.config.Aliyun;
import cloud.unionj.generator.service.apicloud.config.AliyunConfigLoad;
import cloud.unionj.generator.service.apicloud.config.ConfigLoad;
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
public class CloudTaskTrigger implements Openapi3Trigger{
    /**
     * 调用云效api
     */
    @Override
    public void call(Openapi3 openapi3, String path) {
        AliyunConfigLoad.load(path);
        createTask(openapi3);
    }

    @Override
    public void call(Openapi3 openapi3, InputStream is) {
        AliyunConfigLoad.load(is);
        createTask(openapi3);
    }

    @Override
    public void call(Openapi3 openapi3, File file) {
        AliyunConfigLoad.load(file);
        createTask(openapi3);
    }

    public void createTask(Openapi3 openapi3){
        Map<String, Path> paths = openapi3.getPaths();
        paths.forEach((k, v) -> {
            Map<String, Operation> map = resolvefromOperation(k, v);
            for (Map.Entry<String, Operation> entry : map.entrySet()) {
                String title = entry.getKey();
                Operation op = entry.getValue();
                CreateDevopsProjectTaskResponse response = TaskHandler.create(tr -> {
                    tr.setContent(title); // 标题
                    tr.setNote(op.getSummary()); // 备注
                    tr.setPriority(0); // 优先级：0：普通（默认值）1：紧急2：非常紧急
                    tr.setStartDate(DateTimeUtils.nowStringByUTC());
                    tr.setDueDate(DateTimeUtils.afterWeekStringByUTC(1L));
                });
                ConsolePrint.pretty(response);
                break;
            }
        });
    }

    public Map<String, Operation> resolvefromOperation(String router, Path path){
        Map<String, Operation> map = Maps.newHashMap();
        StringBuilder title = new StringBuilder();
        if (path.getGet() != null){
            map.put(title.append("GET: ").append(router).toString(), path.getGet());
        }else if(path.getPost() != null){
            map.put(title.append("POST: ").append(router).toString(), path.getPost());
        }else if(path.getDelete() != null){
            map.put(title.append("DELETE: ").append(router).toString(), path.getDelete());
        }else {
            map.put(title.append("PUT: ").append(router).toString(), path.getPut());
        }
        return map;
    }
}
