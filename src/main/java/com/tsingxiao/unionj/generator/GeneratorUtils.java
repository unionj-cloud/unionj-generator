package com.tsingxiao.unionj.generator;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator
 * @date:2020/11/26
 */
public class GeneratorUtils {

  public static String getOutputDir(String outputDir) {
    if (StringUtils.isBlank(outputDir)) {
      outputDir = System.getProperty("user.dir");
    } else {
      File file = new File(outputDir);
      if (!file.isAbsolute()) {
        outputDir = System.getProperty("user.dir") + File.separator + outputDir;
      }
      file = new File(outputDir);
      if (!file.exists()) {
        file.mkdirs();
      }
    }
    return outputDir;
  }

  @SneakyThrows
  public static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) {
    if (fileToZip.isHidden()) {
      return;
    }
    if (fileToZip.isDirectory()) {
      if (fileName.endsWith("/")) {
        zipOut.putNextEntry(new ZipEntry(fileName));
        zipOut.closeEntry();
      } else {
        zipOut.putNextEntry(new ZipEntry(fileName + "/"));
        zipOut.closeEntry();
      }
      File[] children = fileToZip.listFiles();
      for (File childFile : children) {
        zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
      }
      return;
    }
    FileInputStream fis = new FileInputStream(fileToZip);
    ZipEntry zipEntry = new ZipEntry(fileName);
    zipOut.putNextEntry(zipEntry);
    byte[] bytes = new byte[1024];
    int length;
    while ((length = fis.read(bytes)) >= 0) {
      zipOut.write(bytes, 0, length);
    }
    fis.close();
  }

//  public static Server api2server(Api api) {
//    Server server = new Server();
//    List<Service> services = new ArrayList<>();
//
//    List<ApiItemVo> apiItemVoList = api.getItems().values().stream().reduce(new ArrayList<>(), (x, y) -> {
//      x.addAll(y);
//      return x;
//    }).stream().map(ApiItem::toApiItemVo).collect(Collectors.toList());
//
//    Map<String, List<ApiItemVo>> serviceMap = apiItemVoList.stream().collect(Collectors.groupingBy(apiItemVo -> {
//      String endpoint = StringUtils.stripStart(apiItemVo.getEndpoint(), "/");
//      if (StringUtils.isBlank(endpoint)) {
//        return "unknown";
//      }
//      String[] split = endpoint.split("/");
//      if (ArrayUtils.isEmpty(split)) {
//        return "unknown";
//      }
//      return split[0];
//    }, Collectors.toList()));
//
//    for(Map.Entry<String, List<ApiItemVo>> entry: serviceMap.entrySet()) {
//      Service service = new Service();
//      service.setName(entry.getKey());
//
//      List<ReqBody> reqBodyList = new ArrayList<>();
//
//      List<ApiItemVo> apiItemVos = entry.getValue();
//      for (ApiItemVo vo: apiItemVos) {
//        ReqBody reqBody = new ReqBody();
//
//
//        reqBody.setName();
//
//
//      }
//
//
//    }
//
//
//
//    return server;
//  }


}
