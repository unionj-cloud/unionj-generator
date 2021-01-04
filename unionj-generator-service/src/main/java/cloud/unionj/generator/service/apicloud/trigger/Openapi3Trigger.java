package cloud.unionj.generator.service.apicloud.trigger;/**
 * @author dingxu
 * @version 1.0
 * date: 2021/1/4 20:36
 */

import cloud.unionj.generator.openapi3.model.Openapi3;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * @description: description
 * @date: 2021-01-04 20:36
 **/
public interface Openapi3Trigger {
    public void call(Openapi3 openapi3);
}
