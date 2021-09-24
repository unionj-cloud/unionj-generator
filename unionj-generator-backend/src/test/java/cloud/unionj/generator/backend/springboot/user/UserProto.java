package cloud.unionj.generator.backend.springboot.user;

import cloud.unionj.generator.openapi3.PathConfig;
import cloud.unionj.generator.openapi3.dsl.IImporter;
import cloud.unionj.generator.openapi3.expression.paths.ParameterBuilder;
import cloud.unionj.generator.openapi3.model.paths.Parameter;

import static cloud.unionj.generator.backend.springboot.user.UserComponents.*;
import static cloud.unionj.generator.openapi3.PathHelper.get;
import static cloud.unionj.generator.openapi3.PathHelper.post;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.string;

/**
 * @author: created by wubin
 * cloud.unionj.guide.gen.user
 * 2021/9/24
 */
public class UserProto implements IImporter {

  @Override
  public void doImport() {
    post("/api/user/register", PathConfig.builder()
        .summary("用户注册接口")
        .tags(new String[]{"用户管理模块", "User"})
        .reqSchema(UserRegisterFormVO)
        .reqSchemaType(PathConfig.SchemaType.FORMDATA)
        .respSchema(ResultVOUserRegisterRespVO)
        .build());

    post("/api/user/edit", PathConfig.builder()
        .summary("用户信息编辑接口")
        .tags(new String[]{"用户管理模块", "User"})
        .parameters(new Parameter[]{
            ParameterBuilder.builder().name("id").description("用户ID").in(Parameter.InEnum.QUERY).required(true).schema(string).build(),
        })
        .reqSchema(UserEditFormVO)
        .reqSchemaType(PathConfig.SchemaType.FORMDATA)
        .respSchema(ResultVOstring)
        .build());

    get("/api/user/detail", PathConfig.builder()
        .summary("用户信息查询接口")
        .tags(new String[]{"用户管理模块", "User"})
        .parameters(new Parameter[]{
            ParameterBuilder.builder().name("id").description("用户ID").in(Parameter.InEnum.QUERY).required(true).schema(string).build(),
        })
        .respSchema(ResultVOUserDetailVO)
        .build());

    get("/api/user/avatar", PathConfig.builder()
        .summary("用户头像下载接口")
        .tags(new String[]{"用户管理模块", "User"})
        .parameters(new Parameter[]{
            ParameterBuilder.builder().name("id").description("用户ID").in(Parameter.InEnum.QUERY).required(true).schema(string).build(),
        })
        .respSchema(ResultVOstring)
        .build());

    post("/api/user/page", PathConfig.builder()
        .summary("用户分页列表接口")
        .tags(new String[]{"用户管理模块", "User"})
        .reqSchema(UserPageReqVO)
        .respSchema(ResultVOPageResultVOUserDetailVO)
        .build());
  }
}
