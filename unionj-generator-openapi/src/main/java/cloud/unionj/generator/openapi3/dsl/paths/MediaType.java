package cloud.unionj.generator.openapi3.dsl.paths;

import cloud.unionj.generator.openapi3.expression.paths.MediaTypeBuilder;

import java.util.function.Consumer;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.openapi3.dsl.paths
 * @date 2020/12/19
 */
public class MediaType {

  protected static MediaTypeBuilder mediaTypeBuilder;

  public static cloud.unionj.generator.openapi3.model.paths.MediaType mediaType(Consumer<MediaTypeBuilder> consumer) {
    mediaTypeBuilder = new MediaTypeBuilder();
    consumer.accept(mediaTypeBuilder);
    cloud.unionj.generator.openapi3.model.paths.MediaType mediaType = mediaTypeBuilder.build();
    return mediaType;
  }

}
