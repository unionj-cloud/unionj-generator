package cloud.unionj.generator.backend.apicloud.trigger;

import cloud.unionj.generator.openapi3.model.Openapi3;

/**
 * @version v0.1 cloud.unionj.generator
 * @author created by Johnny Ting
 * description: description
 * date: 2021-01-04 20:39
 **/
public class CloudTrigger implements Openapi3Trigger{
    /**
     * 调用云效api
     */
    @Override
    public void create(Openapi3 openapi3) {
        new CloudProjectTrigger().create(openapi3);
        new CloudTaskTrigger().create(openapi3);
    }

    public static void call(Openapi3 openapi3){
        new CloudProjectTrigger().create(openapi3);
        new CloudTaskTrigger().create(openapi3);
    }


}
