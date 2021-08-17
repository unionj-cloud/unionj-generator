package cloud.unionj.generator.ui.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Data
@Component
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {
  private String username;
  private String password;
}
