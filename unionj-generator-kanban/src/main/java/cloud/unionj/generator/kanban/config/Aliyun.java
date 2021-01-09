package cloud.unionj.generator.kanban.config;

import lombok.Data;

/**
 * @version v0.1 cloud.unionj.generator
 * @author created by Johnny Ting
 * description: description
 * date: 2021-01-04 09:40
 **/
@Data
public class Aliyun{
    private String endPoint;
    private String regionId;
    private String accessKeyId;
    private String secret;
    private String orgId;
    private String projectId;
    private String executorId;
}
