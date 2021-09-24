package cloud.unionj.generator.ui.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class DocAuthProperties {
  @Value("${doc.auth.username:}")
  private String username;
  @Value("${doc.auth.password:}")
  private String password;
  @Value("${spring.mvc.servlet.path:}")
  private String prefix;
}
