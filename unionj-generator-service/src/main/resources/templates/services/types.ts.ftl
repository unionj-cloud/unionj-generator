<#list types as type>
export interface ${type.name} {
<#list type.properties as property>
    ${property.name}<#if !property.required>?</#if>: ${property.type};
</#list>
}

</#list>
