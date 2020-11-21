import { rest } from 'msw'

const baseUrl = '${baseUrl}'

export const handlers = [
<#list apis as api>
    rest.${api.method}(baseUrl + '${api.endpoint}', (req, res, ctx) => {
    <#if api.pathParams??>
        const {
        <#list api.pathParams as pathParam>
            ${pathParam},
        </#list>
        } = req.params
    </#if>
    <#if api.bodyParams??>
        const {
        <#list api.bodyParams as bodyParam>
            ${bodyParam},
        </#list>
        } = req.body
    </#if>
<#if api.queryParams??>
    <#list api.queryParams as queryParam>
        const ${queryParam} = req.url.searchParams.get('${queryParam}')
    </#list>
</#if>
    <#if api.headerParams??>
        const {
        <#list api.headerParams as headerParam>
            ${headerParam},
        </#list>
        } = req.headers
    </#if>
        return res(
            ctx.status(200),
            ctx.set('Content-Type', 'application/json'),
        <#if api.responseStr??>
            ctx.body(`${api.responseStr}`),
        </#if>
        )
    }),

</#list>
]
