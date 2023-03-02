package cloud.unionj.generator.openapi3.model;

import cloud.unionj.generator.openapi3.NullAwareBeanUtilsBean;
import cloud.unionj.generator.openapi3.model.info.Info;
import cloud.unionj.generator.openapi3.model.paths.Path;
import cloud.unionj.generator.openapi3.model.servers.Server;
import cloud.unionj.generator.openapi3.model.tags.Tag;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.openapi3.model
 * date 2020/12/14
 */
@Data
public class Openapi3 {

  private String openapi = "3.0.2";
  private Info info;
  private List<Server> servers = new ArrayList<>();
  private List<Tag> tags = new ArrayList<>();
  private Map<String, Path> paths = new LinkedHashMap<>();
  private Components components = new Components();

  public void servers(Server server) {
    this.servers.add(server);
  }

  public void tags(Tag tag) {
    this.tags.add(tag);
  }

  @SneakyThrows
  public void paths(Path path) {
    Path origin = this.paths.get(path.getEndpoint());
    if (origin != null) {
      BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
      notNull.copyProperties(path, origin);
    }
    this.paths.put(path.getEndpoint(), path);
  }

  // TODO
  private ExternalDocs externalDocs;
}
