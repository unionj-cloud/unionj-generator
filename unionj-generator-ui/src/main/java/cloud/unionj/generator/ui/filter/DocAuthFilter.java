package cloud.unionj.generator.ui.filter;

import cloud.unionj.generator.ui.config.DocAuthProperties;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
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
@WebFilter(filterName = "DocAuthFilter", urlPatterns = "/**")
@RequiredArgsConstructor
@Slf4j
public class DocAuthFilter implements Filter {
  private final DocAuthProperties docAuthProperties;
  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String BASIC = "Basic";
  private static final String SPLIT = ":";
  private static final String DOC_PREFIX = "/unionj-cloud";
  private static final String DEFAULT_USER_PASSWORD = "admin";

  @SneakyThrows
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    String token = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
    String prefix = docAuthProperties.getPrefix();
    if(httpServletRequest.getRequestURI().startsWith(prefix + DOC_PREFIX)){
      boolean passed = false;
      if(StringUtils.isNotBlank(token)){
        String userNameAndPassword = StringUtils.removeStart(token, BASIC).trim();
        if(StringUtils.isNotBlank(userNameAndPassword)){
          userNameAndPassword = new String(Base64.decode(userNameAndPassword));
          String[] params = userNameAndPassword.split(SPLIT);
          String username;
          String password;
          if(StringUtils.isBlank(docAuthProperties.getUsername()) || StringUtils.isBlank(docAuthProperties.getPassword())){
            username = DEFAULT_USER_PASSWORD;
            password = DEFAULT_USER_PASSWORD;
          }else {
            username = docAuthProperties.getUsername();
            password = docAuthProperties.getPassword();
          }
          if (username.equals(params[0]) && password.equals(params[1])) {
            passed = true;
          }
        }
      }
      if(!passed){
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setHeader("WWW-Authenticate", "Basic realm=provide username and password");
        httpServletResponse.getWriter().write(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        return;
      }
    }
    chain.doFilter(request, response);
  }

}
