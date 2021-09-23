package cloud.unionj.generator.ui.controller;

import cloud.unionj.generator.openapi3.model.Openapi3;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.ui
 * @date:2021/8/16
 */
@Controller
@RequestMapping("/unionj-cloud")
public class UIController {

  private static String entry = null;
  private static Openapi3 openAPI = new Openapi3();

  @Value("${cloud.unionj.generator.entry:gen.Openapi3Designer.design}")
  public void setPrivateName(String privateName) {
    UIController.entry = privateName;
  }

  @PostConstruct
  public void init() {
    String designClass = UIController.entry.substring(0, UIController.entry.lastIndexOf("."));
    String designMethod = UIController.entry.substring(UIController.entry.lastIndexOf(".") + 1);
    try {
      Class<?> designer = UIController.class.getClassLoader().loadClass(designClass);
      Method design = designer.getMethod(designMethod);
      openAPI = (Openapi3) design.invoke(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @SneakyThrows
  @GetMapping("/doc")
  public String doc(HttpServletRequest request, Model model) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    model.addAttribute("doc", objectMapper.writeValueAsString(openAPI));
    String full = request.getRequestURL().toString();
    String uri = request.getRequestURI();
    String servletPath = request.getServletPath();
    if (servletPath.equals(uri)) {
      servletPath = "";
    }
    String docUrl = StringUtils.removeEnd(full, uri) + servletPath + "/unionj-cloud/openapi3.json";
    model.addAttribute("docUrl", docUrl);
    return "index";
  }

  @SneakyThrows
  @GetMapping("/openapi3.json")
  @ResponseBody
  public Openapi3 openapi3() {
    return openAPI;
  }
}
