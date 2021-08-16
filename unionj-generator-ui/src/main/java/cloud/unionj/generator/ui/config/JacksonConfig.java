package cloud.unionj.generator.ui.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfig {
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer customizer() {
    return builder -> {
      builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
      builder.locale(Locale.CHINA);
      builder.failOnUnknownProperties(false);
      builder.failOnEmptyBeans(false);
      builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
    };
  }
}
