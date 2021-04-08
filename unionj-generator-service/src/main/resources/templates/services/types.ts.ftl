<#list types as type>
export interface ${type.name} {
<#list type.properties as property>
    ${property.name}<#if !property.required>?</#if>: ${property.type};
</#list>
}

<#if type.enumTypes?has_content>
<#list type.enumTypes as enumType>
export enum ${enumType.name} {
<#if enumType.enums?has_content>
<#list enumType.enums as enum>
    ${enum.name} = "${enum.value}",
</#list>
</#if>
}
</#list>
</#if>

</#list>
