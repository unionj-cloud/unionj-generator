package cloud.unionj.generator.service.apicloud.trigger;

import cloud.unionj.generator.openapi3.model.Openapi3;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * @description: description
 * @date: 2021-01-04 20:36
 **/
public interface Openapi3Trigger {
    void call(Openapi3 openapi3);
}
