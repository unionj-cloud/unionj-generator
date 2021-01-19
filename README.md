#### [中文](./README_zh.md)
# unionj-generator
unionj-generator is a collection of code generators with a restful api design tool(DSL) implementing 
[openAPI 3.0.0 specification](http://spec.openapis.org/oas/v3.0.3).

Including：
- apidoc: api documentation website using [shins](https://github.com/mermade/shins)
- backend: generating VO and Proto(interface for controller to implement) for spring boot backend
- frontend: generating typescript vue project with axios api clients built-in 
- mock: generating request handlers for [mswjs](https://github.com/mswjs/msw) 
- openapi: dsl implementing [openAPI 3.0.0 specification](http://spec.openapis.org/oas/v3.0.3)
- service: generating typescript axios api clients
- kanban: generating todo cards for Aliyun YunXiao(云效) project management platform 

### Installation
There are two options.
- Copy the following code to pom.xml
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

- Clone this repository and execute the following command, then move the jars into your project
```
mvn clean install -Dmaven.test.skip=true
```

### Usage Example
#### DSL
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
#### Generating backend code
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

### Integrated Aliyun YunXiao(云效) API

      [Aliyun API Documents](https://help.aliyun.com/document_detail/179127.html?spm=a2c4g.11186623.6.701.14a335b5pN0T3H)

1. Need to configure aliyun secret、accessKeyId、 yunxiao's orgId and api__invoker executorId. Template file directory：
   
   ```java
   /unionj-generator/unionj-generator-kanban/src/main/resources/aliyun.template.properties
   ```

2. Can create yunxiao's projects and tasks.
   
   ```java
   # info's title、description are mapped to yunxiao project's title and description
   
   # the same, path's router、summary are mapped to yunxiao task's title and description
   
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

3. Talk is cheap, show me the code.
   
   ```java
   Openapi3 openapi3 = openapi3(ob -> {
        omitted...
   })
   
   AliyunConfigLoad.load("/Users/dingxu/config/aliyun.properties");
   CloudTrigger.call(openapi3);
   ```

4. Todo...

### DSL tutorial

