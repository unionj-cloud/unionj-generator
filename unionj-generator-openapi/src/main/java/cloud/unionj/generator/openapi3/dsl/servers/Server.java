package cloud.unionj.generator.openapi3.dsl.servers;

import cloud.unionj.generator.openapi3.dsl.Openapi3;
import cloud.unionj.generator.openapi3.expression.servers.ServerBuilder;

import java.util.function.Consumer;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.dsl
 * date 2020/12/14
 */
public class Server extends Openapi3 {

  public static void server(Consumer<ServerBuilder> consumer) {
    ServerBuilder serverBuilder = new ServerBuilder();
    consumer.accept(serverBuilder);
    cloud.unionj.generator.openapi3.model.servers.Server server = serverBuilder.build();
    openapi3Builder.servers(server);
  }

}
