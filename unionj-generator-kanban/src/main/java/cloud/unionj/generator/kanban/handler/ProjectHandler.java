package cloud.unionj.generator.kanban.handler;

import cloud.unionj.generator.kanban.config.AliyunConfigLoad;
import cloud.unionj.generator.kanban.model.Member;
import cloud.unionj.generator.kanban.utils.AcsClient;
import com.aliyuncs.devops_rdc.model.v20200303.CreateDevopsProjectRequest;
import com.aliyuncs.devops_rdc.model.v20200303.CreateDevopsProjectResponse;
import com.aliyuncs.devops_rdc.model.v20200303.InsertProjectMembersRequest;
import com.aliyuncs.devops_rdc.model.v20200303.InsertProjectMembersResponse;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @version v0.1 cloud.unionj.generator
 * @author created by Johnny Ting
 * description: description
 * date: 2021-01-05 11:01
 **/
@Slf4j
public class ProjectHandler extends AliyunConfigLoad {
    /**
     * 创建云效项目
     * @param consumer
     * @return
     */
    @SneakyThrows
    public static CreateDevopsProjectResponse create(Consumer<CreateDevopsProjectRequest> consumer){
        CreateDevopsProjectRequest createProjectRequest = new CreateDevopsProjectRequest();
        createProjectRequest.setOrgId(AliyunConfig.getOrgId());
        createProjectRequest.setSysEndpoint(AliyunConfig.getEndPoint());
        consumer.accept(createProjectRequest);
        CreateDevopsProjectResponse response = AcsClient.get().getAcsResponse(createProjectRequest);
        if (response.getSuccess()){
            String projectId = response.getObject();
            // 将自己加入项目
            ArrayList<Member> members = Lists.newArrayList(new Member(AliyunConfig.getExecutorId(), 0));
            addMember(members, projectId);
        }else{
            log.error("创建项目出错！！！");
        }
        return response;
    }

    @SneakyThrows
    public static void addMember(List<Member> members, String projectId){
        InsertProjectMembersRequest addProjectMembersRequest =new InsertProjectMembersRequest();
        addProjectMembersRequest.setOrgId(AliyunConfig.getOrgId());
        addProjectMembersRequest.setProjectId(projectId);
        String memStr = JSONObject.valueToString(members);
        log.info(memStr);
        addProjectMembersRequest.setMembers(memStr);
        addProjectMembersRequest.setSysEndpoint(AliyunConfig.getEndPoint());
        InsertProjectMembersResponse acsResponse = AcsClient.get().getAcsResponse(addProjectMembersRequest);
        if (!acsResponse.getSuccessful()){
            log.error("添加成员出错！！！");
        }
    }

}
