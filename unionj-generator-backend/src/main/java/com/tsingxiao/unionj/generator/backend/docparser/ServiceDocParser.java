package com.tsingxiao.unionj.generator.backend.docparser;

import com.tsingxiao.unionj.generator.openapi3.model.Openapi3;
import com.tsingxiao.unionj.generator.openapi3.parser.Openapi3Parser;
import com.tsingxiao.unionj.generator.service.docparser.entity.BizServer;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.mock.docparser
 * @date:2020/11/18
 */
@Data
public class ServiceDocParser {

  public static BizServer parse(File doc) throws IOException {
    Openapi3 openAPI = new Openapi3Parser().parse(doc);
    return BizServer.convert(openAPI);
  }

  public static BizServer parse(URL doc) throws IOException {
    Openapi3 openAPI = new Openapi3Parser().parse(doc);
    return BizServer.convert(openAPI);
  }

  public static BizServer parse(InputStream doc) throws IOException {
    Openapi3 openAPI = new Openapi3Parser().parse(doc);
    return BizServer.convert(openAPI);
  }
}
