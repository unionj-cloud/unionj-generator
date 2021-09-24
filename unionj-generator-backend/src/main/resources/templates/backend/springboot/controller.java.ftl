package ${packageName};

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import ${voPackageName}.*;
import ${protoPackageName}.${protoName};
import ${servicePackageName}.${serviceName};
<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>

@RestController
@RequiredArgsConstructor
public class ${name} implements ${protoName} {

    private final ${serviceName} ${serviceName?uncap_first};

    <#list routers as router>
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
    ) {
        return ${serviceName?uncap_first}.${router.name}(
        <#assign x>
            <#if router.pathParams??>
                <#list router.pathParams as pathParam>
                    ${pathParam.name},
                </#list>
            </#if>
            <#if router.queryParams??>
                <#list router.queryParams as queryParam>
                    ${queryParam.name},
                </#list>
            </#if>
            <#if router.reqBody??>
                ${router.reqBody.name},
            </#if>
            <#if router.file??>
                ${router.file.name},
            </#if>
        </#assign>
        ${x?keep_before_last(",")?trim}
        );
    }
    <#sep>

    </#sep>
    </#list>

}
