import { AxiosError } from "axios";

export default (error: AxiosError): Promise<AxiosError> => {
  if (error.response) {
    // TODO
    // add your logic to handle error
  }
  return Promise.reject(error);
};
