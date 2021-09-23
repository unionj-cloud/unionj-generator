package ${packageName};

import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import ${voPackageName}.*;
<#if imports??>
    <#list imports as import>
        import ${import};
    </#list>
</#if>

public interface ${name} {

<#list routers as router>
    ${router.respData.type} ${router.name}(
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
    );
    <#sep>

    </#sep>
</#list>

}
