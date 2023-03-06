package cloud.unionj;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import cloud.unionj.generator.backend.docparser.BackendDocParser;
import cloud.unionj.generator.backend.docparser.entity.Backend;
import cloud.unionj.generator.backend.springboot.OutputConfig;
import cloud.unionj.generator.backend.springboot.SpringbootFolderGenerator;
import cloud.unionj.generator.openapi3.model.Openapi3;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Mojo(name = "codegen", defaultPhase = LifecyclePhase.COMPILE, requiresDependencyResolution = ResolutionScope.COMPILE)
public class Codegen extends AbstractMojo {

  @Parameter(defaultValue = "${project}", required = true, readonly = true)
  MavenProject project;

  @Parameter(property = "entry")
  String entry;

  @Parameter(property = "protoPkg")
  String protoPkg;

  @Parameter(property = "protoDir")
  String protoDir;

  @Parameter(property = "voPkg")
  String voPkg;

  @Parameter(property = "voDir")
  String voDir;

  @Parameter(property = "controllerPkg")
  String controllerPkg;

  @Parameter(property = "controllerDir")
  String controllerDir;

  @Parameter(property = "servicePkg")
  String servicePkg;

  @Parameter(property = "serviceDir")
  String serviceDir;

  @Parameter(property = "parentGroupId")
  String parentGroupId;

  @Parameter(property = "parentArtifactId")
  String parentArtifactId;

  @Parameter(property = "parentVersion")
  String parentVersion;

  @Parameter(property = "feignPkg")
  String feignPkg;

  @Parameter(property = "feignDir")
  String feignDir;

  @Parameter(property = "serviceId")
  String serviceId;

  @Parameter(property = "serviceBaseUrlKey")
  String serviceBaseUrlKey;

  @Parameter(property = "packages")
  List<SpringbootFolderGenerator.Package> packages;

  @Parameter(property = "docUrl")
  String docUrl;

  @Parameter(property = "docFile")
  String docFile;

  @Parameter(property = "noDefaultComment")
  boolean noDefaultComment;

  @Parameter(property = "companyName")
  String companyName;

  @Parameter(property = "author")
  String author;

  @Parameter(property = "copyright")
  String copyright;

  private ClassLoader getClassLoader(MavenProject project) {
    try {
      List classpathElements = project.getCompileClasspathElements();
      classpathElements.add(project.getBuild()
                                   .getOutputDirectory());
      classpathElements.add(project.getBuild()
                                   .getTestOutputDirectory());
      URL urls[] = new URL[classpathElements.size()];
      for (int i = 0; i < classpathElements.size(); ++i) {
        urls[i] = new File((String) classpathElements.get(i)).toURL();
      }
      return new URLClassLoader(urls, this.getClass()
                                          .getClassLoader());
    } catch (Exception e) {
      return this.getClass()
                 .getClassLoader();
    }
  }

  @SneakyThrows
  public void execute() {
    System.out.println(project.getArtifactId());
    System.out.println(project.getBasedir());
    System.out.println(project.getModules());
    for (Object module : project.getModules()) {
      System.out.println(module);
    }
    Backend backend;
    if (StringUtils.isNotEmpty(this.docUrl)) {
      URL url = new URL(this.docUrl);
      URLConnection uc = url.openConnection();
      String basicAuth = "Basic " + new String(Base64.getEncoder()
                                                     .encode(url.getUserInfo()
                                                                .getBytes()));
      uc.setRequestProperty("Authorization", basicAuth);
      try (BufferedInputStream in = new BufferedInputStream(uc.getInputStream())) {
        backend = BackendDocParser.parse(in);
      }
    } else if (StringUtils.isNotEmpty(this.docFile)) {
      try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(this.docFile))) {
        backend = BackendDocParser.parse(in);
      }
    } else {
      String designClass = "gen.Openapi3Designer";
      String designMethod = "design";
      if (this.entry != null && this.entry.length() > 0) {
        designClass = StringUtils.substring(this.entry, 0, StringUtils.lastIndexOf(this.entry, "."));
        designMethod = StringUtils.substring(this.entry, StringUtils.lastIndexOf(this.entry, ".") + 1);
      }
      System.out.println(designClass);
      System.out.println(designMethod);
      Openapi3 openAPI = null;
      try {
        Class<?> designer = this.getClassLoader(project)
                                .loadClass(designClass);
        Method design = designer.getMethod(designMethod);
        openAPI = (Openapi3) design.invoke(null);
      } catch (Exception e) {
        e.printStackTrace();
        return;
      }
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      File oas3SpecFile = new File("openapi3.json");
      FileUtils.writeStringToFile(oas3SpecFile, objectMapper.writeValueAsString(openAPI),
                                  StandardCharsets.UTF_8.name());
      backend = BackendDocParser.parse(openAPI);
    }
    SpringbootFolderGenerator.Builder builder = new SpringbootFolderGenerator.Builder(backend).pomProject(true)
                                                                                              .pomParentGroupId(
                                                                                                  parentGroupId)
                                                                                              .pomParentArtifactId(
                                                                                                  parentArtifactId)
                                                                                              .pomParentVersion(
                                                                                                  parentVersion);
    String protoArtifactId = null;
    String voArtifactId = null;
    String controllerArtifactId = null;
    String serviceArtifactId = null;
    String feignArtifactId = null;
    String[] split;
    if (this.protoDir != null && this.protoPkg != null) {
      split = this.protoDir.split("/");
      protoArtifactId = split[split.length - 1];
      builder.protoOutput(new OutputConfig(protoPkg, this.protoDir));
      builder.pomProtoArtifactId(protoArtifactId);
    }
    if (this.voDir != null && this.voPkg != null) {
      split = this.voDir.split("/");
      voArtifactId = split[split.length - 1];
      builder.voOutput(new OutputConfig(voPkg, this.voDir));
      builder.pomVoArtifactId(voArtifactId);
    }
    if (this.controllerDir != null && this.controllerPkg != null) {
      split = this.controllerDir.split("/");
      controllerArtifactId = split[split.length - 1];
      builder.controllerOutput(new OutputConfig(controllerPkg, this.controllerDir));
      builder.pomControllerArtifactId(controllerArtifactId);
    }
    if (this.serviceDir != null && this.servicePkg != null) {
      split = this.serviceDir.split("/");
      serviceArtifactId = split[split.length - 1];
      builder.serviceOutput(new OutputConfig(servicePkg, this.serviceDir));
      builder.pomServiceArtifactId(serviceArtifactId);
    }
    if (this.feignDir != null && this.feignPkg != null) {
      split = this.feignDir.split("/");
      feignArtifactId = split[split.length - 1];
      builder.feignOutput(new OutputConfig(feignPkg, this.feignDir));
      builder.pomFeignArtifactId(feignArtifactId);
    }
    for (SpringbootFolderGenerator.Package pkg : this.packages) {
      builder.pkg(pkg);
    }
    if (this.noDefaultComment) {
      builder.noDefaultComment(this.noDefaultComment);
    }
    builder.companyName(this.companyName);
    builder.author(this.author);
    builder.copyright(this.copyright);
    SpringbootFolderGenerator springbootFolderGenerator = builder.serviceId(this.serviceId)
                                                                 .serviceBaseUrlKey(this.serviceBaseUrlKey)
                                                                 .build();
    springbootFolderGenerator.generate();
    getLog().info("Code generated");
  }
}
