# unionj-generator

根据OpenAPI3规范，使用本项目DSL生成前后端代码。

# 集成阿里云云效API

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
