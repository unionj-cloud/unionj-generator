export interface ResultDto {
    code: number;
    data: any;
    msg: string;
    ok: boolean;
}

export type Result = ResultDto | Blob

<#list types as type>
export interface ${type.name} {
<#list type.properties as property>
    ${property.name}: ${property.type};
</#list>
}

</#list>
