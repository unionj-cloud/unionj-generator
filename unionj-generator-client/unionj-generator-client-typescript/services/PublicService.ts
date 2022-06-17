/**
* Generated by unionj-generator.
* Don't edit!
*
* @module Public
*/
import { CreateAxiosOptions } from "pullcode/src/httputil/axiosTransform";
import BizService from "./BizService";
import type {
  PublicLogInResp,
  PublicSignUpResp,
  PublicTokenValidateResp,
} from "./types"

export class PublicService extends BizService{

  constructor(options?: Partial<CreateAxiosOptions>) {
    super(options);
  }

  /**
  * GET /public/download/avatar
  *
  * GetPublicDownloadAvatar ����ͷ��ӿ�
  * չʾ��ζ����ļ����ؽӿ�
  * ����ǩ���ĳ������������ֻ��һ��*os.File���͵Ĳ���
  * GetPublicDownloadAvatar is avatar download api
  * demo how to define file download api
  * NOTE: there must be one and at most one *os.File output parameter
  * @param userId �û�ID
user id
  * @returns Promise<any> 
  */
  getPublicDownloadAvatar(
    params: {
      userId: number,
    },
  ) :Promise<any> {
    return this.getAxios().get(`/public/download/avatar`,
          {
            params: {
              userId: params.userId,
            },
            responseType: 'blob',
          }
        )
  }

  /**
  * POST /public/log/in
  *
  * PublicLogIn �û���¼�ӿ�
  * չʾ��μ�Ȩ������token
  * PublicLogIn is user login api
  * demo how to do authentication and issue token
  * @param password ����
password
  * @param username �û���
username
  * @returns Promise<PublicLogInResp> 
  */
  postPublicLogIn(
    params: {
      password: string,
      username: string,
    },
  ) :Promise<PublicLogInResp> {
      const urlSearchParams = new URLSearchParams();
         urlSearchParams.append('password', '' + params.password);
         urlSearchParams.append('username', '' + params.username);
    return this.getAxios().post(`/public/log/in`,
          urlSearchParams,
          {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
            },
          }
        )
  }

  /**
  * POST /public/sign/up
  *
  * PublicSignUp �û�ע��ӿ�
  * չʾ��ζ���POST������Content-Type��application/x-www-form-urlencoded�Ľӿ�
  * PublicSignUp is user signup api
  * demo how to define post request api which accepts application/x-www-form-urlencoded content-type
  * @param code ͼ����֤��
image code
  * @param password ����
password
  * @param username �û���
username
  * @returns Promise<PublicSignUpResp> 
  */
  postPublicSignUp(
    params: {
      code?: string,
      password: string,
      username: string,
    },
  ) :Promise<PublicSignUpResp> {
      const urlSearchParams = new URLSearchParams();
        params.code !== undefined && urlSearchParams.append('code', '' + params.code);
         urlSearchParams.append('password', '' + params.password);
         urlSearchParams.append('username', '' + params.username);
    return this.getAxios().post(`/public/sign/up`,
          urlSearchParams,
          {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
            },
          }
        )
  }

  /**
  * POST /public/token/validate
  *
  * PublicTokenValidate tokenУ��ӿ�
  * ���token��Ч�������û���Ϣ
  * PublicTokenValidate validates token string
  * if token is valid, return user information
  * @param token 
  * @returns Promise<PublicTokenValidateResp> 
  */
  postPublicTokenValidate(
    params: {
      token: string,
    },
  ) :Promise<PublicTokenValidateResp> {
      const urlSearchParams = new URLSearchParams();
         urlSearchParams.append('token', '' + params.token);
    return this.getAxios().post(`/public/token/validate`,
          urlSearchParams,
          {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
            },
          }
        )
  }

}

export default PublicService;
  
export function createPublicService(opt?: Partial<CreateAxiosOptions>) {
  return new PublicService(opt);
}

export const publicService = createPublicService();
