package cloud.unionj.generator.ui.filter;

import cloud.unionj.generator.ui.config.AuthProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(filterName = "AuthFilter", urlPatterns = "/**")
@RequiredArgsConstructor
@Slf4j
public class AuthFilter implements Filter {

  private final AuthProperties authProperties;
  private final PathMatcher pathMatcher = new AntPathMatcher();
  private static final String AUTHORIZATION_HEADER = "Authorization";

  @Value("${spring.mvc.servlet.path}")
  private String prefix;

  @SneakyThrows
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    try {
      HttpServletRequest httpServletRequest = (HttpServletRequest) request;

      String token = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
      token = StringUtils.removeStart(token, "Bearer ");
      boolean tokenIsValid = false;
      HttpServletResponse httpServletResponse = (HttpServletResponse) response;
      httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

      chain.doFilter(request, response);
    } finally {
    }
  }

}
