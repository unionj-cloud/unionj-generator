# unionj-generator

Generate mswjs handlers from openapi documents

# Integrated Aliyun YunXiao(云效) API

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
