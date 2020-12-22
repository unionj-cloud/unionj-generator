package ${packageName};

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>

<#if base??>
@RequestMapping("${base}")
</#if>
public interface ${name} {

    <#list routers as router>
    @${router.httpMethod?capitalize}Mapping("${router.endpoint}")
    ${router.respData.type} ${router.name}(
        <#assign x>
            <#if router.pathParams??>
                <#list router.pathParams as pathParam>
        <#if pathParam.required>@PathVariable("${pathParam.name}")<#else>@PathVariable(value="${pathParam.name}", required=false)</#if> ${pathParam.type} ${pathParam.name},
                </#list>
            </#if>
            <#if router.queryParams??>
                <#list router.queryParams as queryParam>
        <#if queryParam.required>@RequestParam("${queryParam.name}")<#else>@RequestParam(value="${queryParam.name}", required=false)</#if> ${queryParam.type} ${queryParam.name},
                </#list>
            </#if>
            <#if router.reqBody??>
        <#if router.reqBody.required>@RequestBody<#else>@RequestBody(required=false)</#if> ${router.reqBody.type} ${router.reqBody.name},
            </#if>
            <#if router.file??>
        <#if router.file.required>@RequestPart("${router.file.name}")<#else>@RequestPart(value="${router.file.name}", required=false)</#if> ${router.file.type} ${router.file.name},
            </#if>
        </#assign>
        ${x?keep_before_last(",")?trim}
    );
    <#sep>

    </#sep>
    </#list>

}
