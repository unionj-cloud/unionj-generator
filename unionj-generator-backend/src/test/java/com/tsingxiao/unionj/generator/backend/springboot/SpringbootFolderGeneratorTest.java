package com.tsingxiao.unionj.generator.backend.springboot;

import com.google.common.collect.Lists;
import com.tsingxiao.unionj.generator.backend.docparser.entity.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.tsingxiao.unionj.generator.backend.springboot.Constants.PACKAGE_NAME;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.backend.springboot
 * @date:2020/12/21
 */
public class SpringbootFolderGeneratorTest {

  private static final String VO_PACKAGE_NAME = PACKAGE_NAME + ".vo";
  private static final String PROTO_PACKAGE_NAME = PACKAGE_NAME + ".proto";

  @Test
  public void generate() {
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
            .file(new ProtoFile.Builder().required(false).build())
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

    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
    springbootFolderGenerator.generate();
  }
}
