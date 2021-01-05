package cloud.unionj.generator.service.apicloud.trigger;

import cloud.unionj.generator.openapi3.model.Openapi3;
import java.io.File;
import java.io.InputStream;

/**
 * @version v0.1 cloud.unionj.generator
 * @author: created by Johnny Ting
 * @description: description
 * @date: 2021-01-04 20:36
 **/
public interface Openapi3Trigger {
    void call(Openapi3 openapi3, String path);
    void call(Openapi3 openapi3, InputStream is);
    void call(Openapi3 openapi3, File file);
}
