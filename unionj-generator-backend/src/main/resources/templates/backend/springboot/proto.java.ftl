package ${packageName};

import org.springframework.core.io.Resource;
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
        @PathVariable ${pathParam.type} ${pathParam.name},
                </#list>
            </#if>
            <#if router.queryParams??>
                <#list router.queryParams as queryParam>
        @RequestParam ${queryParam.type} ${queryParam.name},
                </#list>
            </#if>
            <#if router.reqBody??>
        @RequestBody ${router.reqBody.type} ${router.reqBody.name},
            </#if>
        </#assign>
        ${x?keep_before_last(",")?trim}
    );
    <#sep>

    </#sep>
    </#list>

}
