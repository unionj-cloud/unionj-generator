package cloud.unionj.generator.backend.springboot;

import cloud.unionj.generator.backend.docparser.BackendDocParser;
import cloud.unionj.generator.backend.docparser.entity.*;
import cloud.unionj.generator.backend.springboot.export.ExportProto;
import cloud.unionj.generator.backend.springboot.user.UserProto;
import cloud.unionj.generator.openapi3.dsl.PathHelper;
import cloud.unionj.generator.openapi3.model.Openapi3;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cloud.unionj.generator.openapi3.dsl.Openapi3.openapi3;
import static cloud.unionj.generator.openapi3.dsl.info.Info.info;
import static cloud.unionj.generator.openapi3.dsl.servers.Server.server;

/**
 * @author created by wubin
 * @version v0.1
 * cloud.unionj.generator.backend.springboot
 * date 2020/12/21
 */
public class SpringbootFolderGeneratorTest {

  private static final String VO_PACKAGE_NAME = Constants.PACKAGE_NAME + ".vo";
  private static final String PROTO_PACKAGE_NAME = Constants.PACKAGE_NAME + ".proto";

  @SneakyThrows
  @Test
  public void test1() {
    Openapi3 openAPI3 = openapi3(ob -> {
      info(ib -> {
        ib.title("用户管理模块");
        ib.version("v1.0.0");
      });

      server(sb -> {
        sb.url("http://unionj.cloud");
      });

      PathHelper.doImport(UserProto.class);
      PathHelper.doImport(ExportProto.class);
    });

    Backend backend = BackendDocParser.parse(openAPI3);
    SpringbootFolderGenerator.Builder builder = new SpringbootFolderGenerator.Builder(backend)
        .pomProject(true)
        .pomParentGroupId("com.github.myproject")
        .pomParentArtifactId("myproject")
        .pomParentVersion("1.0.1-SNAPSHOT");

    builder.protoOutput(new OutputConfig("com.github.myproject.myproto",
        "myproject-proto"));
    builder.pomProtoArtifactId("myproject-proto");

    builder.voOutput(new OutputConfig("com.github.myproject.vo",
        "myproject-vo"));
    builder.pomVoArtifactId("myproject-vo");

    builder.controllerOutput(new OutputConfig("com.github.myproject.controller",
        "myproject-controller"));
    builder.pomControllerArtifactId("myproject-controller");

    builder.serviceOutput(new OutputConfig("com.github.myproject.service",
        "myproject-service"));
    builder.pomServiceArtifactId("myproject-service");

    SpringbootFolderGenerator springbootFolderGenerator = builder.build();
    springbootFolderGenerator.generate();
  }

  @Test
  public void generate() throws IOException {
    Backend backend = new Backend();
    List<Vo> voList = new ArrayList<>();
    Vo vo = new Vo();
    vo.setName("PetVo");
    List<VoProperty> voPropertyList = Lists.newArrayList(
        new VoProperty("id", "id", Long.class.getSimpleName()),
        new VoProperty("name", "name", String.class.getSimpleName()),
        new VoProperty("category", "category", "CategoryVo"),
        new VoProperty("photoUrls", "photoUrls", "List<String>"),
        new VoProperty("tags", "tags", "List<TagVo>"),
        new VoProperty("status", "status", "StatusEnum")
    );
    vo.setProperties(voPropertyList);
    List<VoEnumType> enumTypeList = Lists.newArrayList(
        new VoEnumType(Lists.newArrayList(
            new VoEnum("AVAILABLE", "available"),
            new VoEnum("PENDING", "pending"),
            new VoEnum("SOLD", "sold")
        ), "StatusEnum")
    );
    vo.setEnumTypes(enumTypeList);
    vo.setImports(Lists.newArrayList(
        List.class.getName()
    ));
    voList.add(vo);


    vo = new Vo();
    vo.setName("CategoryVo");
    voPropertyList = Lists.newArrayList(
        new VoProperty("id", "id", Long.class.getSimpleName()),
        new VoProperty("name", "name", String.class.getSimpleName())
    );
    vo.setProperties(voPropertyList);
    voList.add(vo);

    vo = new Vo();
    vo.setName("TagVo");
    voPropertyList = Lists.newArrayList(
        new VoProperty("id", "id", Long.class.getSimpleName()),
        new VoProperty("name", "name", String.class.getSimpleName())
    );
    vo.setProperties(voPropertyList);
    voList.add(vo);

    vo = new Vo();
    vo.setName("ApiResponse");
    voPropertyList = Lists.newArrayList(
        new VoProperty("code", "code", Integer.class.getSimpleName()),
        new VoProperty("type", "type", String.class.getSimpleName()),
        new VoProperty("message", "message", String.class.getSimpleName())
    );
    vo.setProperties(voPropertyList);
    voList.add(vo);

    backend.setVoList(voList);


    List<Proto> protoList = new ArrayList<>();
    Proto proto = new Proto();
    proto.setName("PetProto");
    proto.setImports(Lists.newArrayList(
        List.class.getName(),
        VO_PACKAGE_NAME + ".PetVo",
        VO_PACKAGE_NAME + ".ApiResponse"
    ));
    List<ProtoRouter> routers = Lists.newArrayList(
        new ProtoRouter.Builder("/pet", "addPet", "post")
            .reqBody(new ProtoProperty.Builder("PetVo").name("body").build())
            .respData(new ProtoProperty.Builder("ResponseEntity<Void>").build())
            .build(),
        new ProtoRouter.Builder("/pet/{petId}", "updatePetWithForm", "post")
            .respData(new ProtoProperty.Builder("ResponseEntity<Void>").build())
            .pathParams(Lists.newArrayList(
                new ProtoProperty.Builder(Long.class.getSimpleName()).name("petId").build()
            ))
            .queryParams(Lists.newArrayList(
                new ProtoProperty.Builder(String.class.getSimpleName()).name("name").required(false).build(),
                new ProtoProperty.Builder(String.class.getSimpleName()).name("status").required(false).build()
            ))
            .build(),
        new ProtoRouter.Builder("/pet/{petId}/uploadImage", "uploadFile", "post")
            .file(ProtoProperty.UPLOAD_FILE_BUILDER.required(false).build())
            .respData(new ProtoProperty.Builder("ApiResponse").build())
            .pathParams(Lists.newArrayList(
                new ProtoProperty.Builder(Long.class.getSimpleName()).name("petId").build()
            ))
            .queryParams(Lists.newArrayList(
                new ProtoProperty.Builder(String.class.getSimpleName()).name("additionalMetadata").required(false).build()
            ))
            .build()
    );
    proto.setRouters(routers);
    protoList.add(proto);

    backend.setProtoList(protoList);

    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).mode(Mode.BASIC).build();
    springbootFolderGenerator.generate();
  }

  @Test
  public void generateCustomDir() throws IOException {
    Backend backend = new Backend();
    List<Vo> voList = new ArrayList<>();
    Vo vo = new Vo();
    vo.setName("PetVo");
    List<VoProperty> voPropertyList = Lists.newArrayList(
        new VoProperty("id", "id", Long.class.getSimpleName()),
        new VoProperty("name", "name", String.class.getSimpleName()),
        new VoProperty("category", "category", "CategoryVo"),
        new VoProperty("photoUrls", "photoUrls", "List<String>"),
        new VoProperty("tags", "tags", "List<TagVo>"),
        new VoProperty("status", "status", "StatusEnum")
    );
    vo.setProperties(voPropertyList);
    List<VoEnumType> enumTypeList = Lists.newArrayList(
        new VoEnumType(Lists.newArrayList(
            new VoEnum("AVAILABLE", "available"),
            new VoEnum("PENDING", "pending"),
            new VoEnum("SOLD", "sold")
        ), "StatusEnum")
    );
    vo.setEnumTypes(enumTypeList);
    vo.setImports(Lists.newArrayList(
        List.class.getName()
    ));
    voList.add(vo);


    vo = new Vo();
    vo.setName("CategoryVo");
    voPropertyList = Lists.newArrayList(
        new VoProperty("id", "id", Long.class.getSimpleName()),
        new VoProperty("name", "name", String.class.getSimpleName())
    );
    vo.setProperties(voPropertyList);
    voList.add(vo);

    vo = new Vo();
    vo.setName("TagVo");
    voPropertyList = Lists.newArrayList(
        new VoProperty("id", "id", Long.class.getSimpleName()),
        new VoProperty("name", "name", String.class.getSimpleName())
    );
    vo.setProperties(voPropertyList);
    voList.add(vo);

    vo = new Vo();
    vo.setName("ApiResponse");
    voPropertyList = Lists.newArrayList(
        new VoProperty("code", "code", Integer.class.getSimpleName()),
        new VoProperty("type", "type", String.class.getSimpleName()),
        new VoProperty("message", "message", String.class.getSimpleName())
    );
    vo.setProperties(voPropertyList);
    voList.add(vo);

    backend.setVoList(voList);


    List<Proto> protoList = new ArrayList<>();
    Proto proto = new Proto();
    proto.setName("PetProto");
    proto.setImports(Lists.newArrayList(
        List.class.getName(),
        VO_PACKAGE_NAME + ".PetVo",
        VO_PACKAGE_NAME + ".ApiResponse"
    ));
    List<ProtoRouter> routers = Lists.newArrayList(
        new ProtoRouter.Builder("/pet", "addPet", "post")
            .reqBody(new ProtoProperty.Builder("PetVo").name("body").build())
            .respData(new ProtoProperty.Builder("ResponseEntity<Void>").build())
            .build(),
        new ProtoRouter.Builder("/pet/{petId}", "updatePetWithForm", "post")
            .respData(new ProtoProperty.Builder("ResponseEntity<Void>").build())
            .pathParams(Lists.newArrayList(
                new ProtoProperty.Builder(Long.class.getSimpleName()).name("petId").build()
            ))
            .queryParams(Lists.newArrayList(
                new ProtoProperty.Builder(String.class.getSimpleName()).name("name").required(false).build(),
                new ProtoProperty.Builder(String.class.getSimpleName()).name("status").required(false).build()
            ))
            .build(),
        new ProtoRouter.Builder("/pet/{petId}/uploadImage", "uploadFile", "post")
            .file(ProtoProperty.UPLOAD_FILE_BUILDER.required(false).build())
            .respData(new ProtoProperty.Builder("ApiResponse").build())
            .pathParams(Lists.newArrayList(
                new ProtoProperty.Builder(Long.class.getSimpleName()).name("petId").build()
            ))
            .queryParams(Lists.newArrayList(
                new ProtoProperty.Builder(String.class.getSimpleName()).name("additionalMetadata").required(false).build()
            ))
            .build()
    );
    proto.setRouters(routers);
    protoList.add(proto);

    backend.setProtoList(protoList);


    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend)
        .protoOutput(new OutputConfig("com.github.myproject.myproto",
            "myproject-proto"))
        .voOutput(new OutputConfig("com.github.myproject.vo",
            "myproject-vo"))
        .controllerOutput(new OutputConfig("com.github.myproject.controller",
            "myproject-controller"))
        .serviceOutput(new OutputConfig("com.github.myproject.service",
            "myproject-service"))
        .pomProject(true)
        .pomParentGroupId("com.github.myproject")
        .pomParentArtifactId("myproject")
        .pomParentVersion("1.0.1-SNAPSHOT")
        .pomProtoArtifactId("myproject-proto")
        .pomVoArtifactId("myproject-vo")
        .pomControllerArtifactId("myproject-controller")
        .pomControllerArtifactId("myproject-service")
//        .mode(Mode.BASIC)
        .build();
    springbootFolderGenerator.generate();
  }

  @Test
  public void generateByDoc() throws IOException {
    try (BufferedInputStream is = new BufferedInputStream(ClassLoader.getSystemResourceAsStream("corpus_openapi3.json"))) {
      Backend backend = BackendDocParser.parse(is);
      SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
      springbootFolderGenerator.generate();
    }
  }

  @Test
  public void testDirExistsFiles() throws IOException {

    File dir = new File("myproto/src/main/java/xxx/yyy/myproto");
    if (dir.exists() && !dir.isDirectory()) {
      throw new UnsupportedOperationException("not dir");
    }

    String[] list = dir.list();
    if (list != null && list.length > 0) {
      System.out.println(":::::::::not empty");

      FileUtils.deleteDirectory(dir);
    } else {
      System.out.println(":::::::::empty");
    }
  }
}
