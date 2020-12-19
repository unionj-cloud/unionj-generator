declare module "vue/types/vue" {
  import { AxiosInstance } from "axios";

  interface Vue {
    $http: AxiosInstance;
  }
}

export default {
  inject: ['$http'],
}

