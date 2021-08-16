package cloud.unionj.generator.ui.controller;

import cloud.unionj.generator.openapi3.model.Openapi3;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

  @SneakyThrows
  @GetMapping("/doc")
  public String doc(Model model) {
    String designClass = "gen.Openapi3Designer";
    String designMethod = "design";
    Class<?> designer = this.getClass().getClassLoader().loadClass(designClass);
    Method design = designer.getMethod(designMethod);
    Openapi3 openAPI = (Openapi3) design.invoke(null);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    model.addAttribute("doc", objectMapper.writeValueAsString(openAPI));
    return "index";
  }

  @SneakyThrows
  @GetMapping("/openapi3.json")
  @ResponseBody
  public Openapi3 openapi3() {
    String designClass = "gen.Openapi3Designer";
    String designMethod = "design";
    Class<?> designer = this.getClass().getClassLoader().loadClass(designClass);
    Method design = designer.getMethod(designMethod);
    Openapi3 openAPI = (Openapi3) design.invoke(null);
    return openAPI;
  }
}
