package ${packageName};

import org.springframework.stereotype.Service;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import ${voPackageName}.*;
<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>

@Service
public class ${name}Impl implements ${name} {

<#list routers as router>
    @SneakyThrows
    @Override
    public ${router.respData.type} ${router.name}(
    <#assign x>
        <#if router.pathParams??>
            <#list router.pathParams as pathParam>
                ${pathParam.type} ${pathParam.name},
            </#list>
        </#if>
        <#if router.queryParams??>
            <#list router.queryParams as queryParam>
                ${queryParam.type} ${queryParam.name},
            </#list>
        </#if>
        <#if router.reqBody??>
            ${router.reqBody.type} ${router.reqBody.name},
        </#if>
        <#if router.file??>
            ${router.file.type} ${router.file.name},
        </#if>
    </#assign>
    ${x?keep_before_last(",")?trim}
    ){
        throw new Exception("Implement me");
    }
    <#sep>

    </#sep>
</#list>

}