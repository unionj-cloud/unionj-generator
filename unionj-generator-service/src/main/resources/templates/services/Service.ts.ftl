/**
* Generated by unionj-generator.
*/
import BizService from "./BizService";
import type {
<#list types as type>
  ${type},
</#list>
} from "./types"


export default class ${name} extends BizService{

  constructor(axios: any) {
    super(axios);
  }

<#list routers as router>
  ${router.name}(
  <#if router.reqBody??>
    ${router.reqBody.name}: ${router.reqBody.type},
  </#if>
  <#if router.allParams??>
    <#list router.allParams as param>
      ${param.name}<#if !param.required>?</#if>: ${param.type},
    </#list>
  </#if>
  ) :Promise<${(router.respData.type=="Blob")?then("any",router.respData.type)}> {
    let client = this.axios.${router.httpMethod?lower_case}
    if(this.axios.$${router.httpMethod?lower_case}) {
      client = this.axios.$${router.httpMethod?lower_case}
    }
    return client(this.addPrefix(`${router.endpoint}`),
        <#if router.reqBody??>
          ${router.reqBody.name},
        <#elseif router.httpMethod?lower_case == "post" || router.httpMethod?lower_case == "put">
          null,
        </#if>
        <#if router.queryParams??>
          {
            params: {
            <#list router.queryParams as queryParam>
              ${queryParam.name},
            </#list>
            },
            <#if router.respData.type == "Blob">
            responseType: 'blob',
            </#if>
          }
        </#if>
        )
  }

</#list>
}

