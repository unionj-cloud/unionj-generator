/**
* Generated by unionj-generator.
* Don't edit!
*
* @module 材料申报模块
*/
import BizService from "./BizService";
import type {
  PostClshenbaoFormSavePayload,
  SearchJobPageResult,
} from "./types"

export class ClshenbaoFormService extends BizService{

  constructor(axios: any) {
    super(axios);
  }

  /**
  * POST /clshenbao/form/save
  *
  * 材料申报：保存form表单
  * @param payload 这是payload
  * @param key 键
  * @param style 样式
  * @returns Promise<SearchJobPageResult> 返回参数
  */
  postClshenbaoFormSave(
    payload: PostClshenbaoFormSavePayload,
    params: {
      key: string,
      style?: string,
    },
  ) :Promise<SearchJobPageResult> {
    let client = this.axios.post
    if(this.axios.$post) {
      client = this.axios.$post
    }
    return client(this.addPrefix(`/clshenbao/form/save`),
          payload,
          {
            params: {
              key: params.key,
              style: params.style,
            },
          }
        )
  }

}

export default ClshenbaoFormService;

