<#list types as type>
export interface ${type.name} {
<#list type.properties as property>
    ${property.name}: ${property.type};
</#list>
}

</#list>
