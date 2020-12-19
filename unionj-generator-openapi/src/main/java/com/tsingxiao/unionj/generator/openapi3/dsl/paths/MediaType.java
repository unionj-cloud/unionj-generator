package com.tsingxiao.unionj.generator.openapi3.dsl.paths;

import com.tsingxiao.unionj.generator.openapi3.expression.paths.MediaTypeBuilder;

import java.util.function.Consumer;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl.paths
 * @date:2020/12/19
 */
public class MediaType {

  protected static MediaTypeBuilder mediaTypeBuilder;

  public static com.tsingxiao.unionj.generator.openapi3.model.paths.MediaType mediaType(Consumer<MediaTypeBuilder> consumer) {
    mediaTypeBuilder = new MediaTypeBuilder();
    consumer.accept(mediaTypeBuilder);
    com.tsingxiao.unionj.generator.openapi3.model.paths.MediaType mediaType = mediaTypeBuilder.build();
    return mediaType;
  }

}
