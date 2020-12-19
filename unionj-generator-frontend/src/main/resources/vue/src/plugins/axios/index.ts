import axios, { AxiosInstance } from "axios";
import _Vue from "vue";
import errorHandler from "./AxiosErrorHandler";
import requestInterceptor from "./RequestInterceptor";
import responseInterceptor from "./ResponseInterceptor";

const axiosPlugin = {
  install(Vue: typeof _Vue, instance: AxiosInstance) {
    Object.defineProperties(Vue.prototype, {
      $http: {
        get: function get() {
          return instance;
        }
      }
    });
  }
};

const axiosInstance = axios.create({
  // your options here
});

// request interceptor
axiosInstance.interceptors.request.use(requestInterceptor, errorHandler);

// response interceptor
axiosInstance.interceptors.response.use(responseInterceptor, errorHandler);

_Vue.use(axiosPlugin, axiosInstance);
