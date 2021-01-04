package cloud.unionj.generator.service.apicloud.trigger;/**
 * @author dingxu
 * @version 1.0
 * date: 2021/1/4 20:39
 */

import cloud.unionj.generator.openapi3.model.Openapi3;
import cloud.unionj.generator.openapi3.model.paths.Path;

import java.util.Map;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * @description: description
 * @date: 2021-01-04 20:39
 **/
public class CloudTrigger implements Openapi3Trigger{
    /**
     * 调用云效api
     */
    @Override
    public void call(Openapi3 openapi3) {
        Map<String, Path> paths = openapi3.getPaths();
        paths.forEach((k, v) -> {
            String router = k;
//            String
        });
    }
}
