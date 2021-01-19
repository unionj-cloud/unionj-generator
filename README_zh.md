#### [English](./README.md)
# unionj-generator

根据[openapi 3.0规范](http://spec.openapis.org/oas/v3.0.3) 实现的一套代码生成工具。
包括以下7个功能模块：
- apidoc: 采用[shins](https://github.com/mermade/shins) 开源库，生成restful api文档
- backend: 生成spring boot项目直接可以用的VO类和Proto类
- frontend: 生成内置有typescript写的api axios客户端的vue前端工程
- mock: 采用[mswjs](https://github.com/mswjs/msw) 开源库，生成api mock代码
- openapi: 支持openapi 3.0规范的dsl库
- service: 生成typescript写的api axios客户端代码
- kanban: 接入阿里云云效2020的开放接口，可以用代码生成云效项目和看板任务

### 安装
- 从maven中央仓库下载，将以下代码拷贝到pom文件里，可以按需取用
```
<dependency>
   <groupId>cloud.unionj</groupId>
   <artifactId>unionj-generator-apidoc</artifactId>
   <version>1.1</version>
</dependency>
<dependency>
   <groupId>cloud.unionj</groupId>
   <artifactId>unionj-generator-frontend</artifactId>
   <version>1.1</version>
</dependency>
<dependency>
   <groupId>cloud.unionj</groupId>
   <artifactId>unionj-generator-backend</artifactId>
   <version>1.1</version>
</dependency>
<dependency>
   <groupId>cloud.unionj</groupId>
   <artifactId>unionj-generator-openapi</artifactId>
   <version>1.1</version>
</dependency>
<dependency>
   <groupId>cloud.unionj</groupId>
   <artifactId>unionj-generator-kanban</artifactId>
   <version>1.1</version>
</dependency>
<dependency>
   <groupId>cloud.unionj</groupId>
   <artifactId>unionj-generator-mock</artifactId>
   <version>1.1</version>
</dependency>
<dependency>
   <groupId>cloud.unionj</groupId>
   <artifactId>unionj-generator-service</artifactId>
   <version>1.1</version>
</dependency>
```

- 也可以克隆本仓库代码到本地，然后在项目根路径下执行以下命令，然后以jar包方式引入项目
```
mvn clean install -Dmaven.test.skip=true
```

### 代码示例
#### dsl
```
public static class OssProto implements IImporter {

 @Override
 public void doImport() {
   path("/oss/upload", pb -> {
     post(ppb -> {
       ppb.summary("上传附件");
       ppb.tags("attachment");

       parameter(para -> {
         para.required(false);
         para.in("query");
         para.name("returnKey");
         para.schema(bool);
       });

       requestBody(rb -> {
         rb.required(true);
         rb.content(content(cb -> {
           cb.multipartFormData(mediaType(mb -> {
             mb.schema(schema(upload -> {
               upload.type("object");
               upload.properties("file", schema(file -> {
                 file.type("string");
                 file.format("binary");
               }));
             }));
           }));
         }));
       });

       responses(rb -> {
         rb.response200(response(rrb -> {
           rrb.content(content(cb -> {
             cb.applicationJson(mediaType(mb -> {
               mb.schema(reference(rrrb -> {
                 rrrb.ref(ResultDTOString.getTitle());
               }));
             }));
           }));
         }));
       });
     });
   });
 }
}

@Test
public void TestPath3() throws JsonProcessingException {
 Openapi3 openapi3 = openapi3(ob -> {
   info(ib -> {
     ib.title("测试");
     ib.version("v1.0.0");
   });

   server(sb -> {
     sb.url("http://www.unionj.com");
   });

   PathHelper.doImport(OssProto.class);

 });
 ObjectMapper objectMapper = new ObjectMapper();
 objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
 objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
 System.out.println(objectMapper.writeValueAsString(openapi3));
}
```

#### 生成后端代码
```
@Test
public void Test() throws IOException {
 Openapi3 openapi3 = openapi3(ob -> {
   info(ib -> {
     ib.title("测试");
     ib.version("v1.0.0");
   });

   server(sb -> {
     sb.url("http://www.unionj.com");
   });

   SchemaHelper.batchImport(Components.class);

   path("/oss/upload", pb -> {
     post(ppb -> {
       ppb.summary("上传附件");
       ppb.tags("attachment");

       parameter(para -> {
         para.required(false);
         para.in("query");
         para.name("returnKey");
         para.schema(bool);
       });

       requestBody(rb -> {
         rb.required(true);
         rb.content(content(cb -> {
           cb.applicationOctetStream(mediaType(mb -> {
             mb.schema(schema(upload -> {
               upload.type("string");
               upload.format("binary");
             }));
           }));
         }));
       });

       responses(rb -> {
         rb.response200(response(rrb -> {
           rrb.content(content(cb -> {
             cb.applicationJson(mediaType(mb -> {
               mb.schema(reference(rrrb -> {
                 rrrb.ref(ResultDTOListUserInteger.getTitle());
               }));
             }));
           }));
         }));
       });
     });
   });
 });
 Backend backend = BackendDocParser.parse(openapi3);
 SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend).build();
 springbootFolderGenerator.generate();
```

### 集成阿里云云效API

    [阿里云云效API文档](https://help.aliyun.com/document_detail/179127.html?spm=a2c4g.11186623.6.701.14a335b5pN0T3H)

1. 需要配置阿里云密钥、云效组织orgId、云效api调用者executorId。模板文件：
   
   ```
   /unionj-generator/unionj-generator-kanban/src/main/resources/aliyun.template.properties
   ```

2. 创建云效项目、任务
   
   ```textile
   # 根据info的title、description对应云效项目的标题、描述。
   
   # 根据Path的router、summary对应云效任务的标题、描述。
   
   openapi3(ob -> {
               info(ib -> {
                   ib.title("测试创建项目、任务流程1");
                   ib.description("项目描述");
                   ib.version("v1.0.0");
               });
               Path.path("/hall/onlineSurvey/list", pb -> {
                   Post.post(ppb -> {
                       ppb.summary("网络调查分页");
                       ppb.tags("hall_onlinesurvey");
                   });
               });
               omitted...
   }
   ```

3. 代码调用示例：
   
   ```java
   Openapi3 openapi3 = openapi3(ob -> {
        omitted...
   })
   
   AliyunConfigLoad.load("/Users/dingxu/config/aliyun.properties");
   CloudTrigger.call(openapi3);
   ```

4. Todo...
