import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "./plugins/antd";
import "./plugins/axios";

Vue.config.productionTip = false;

if (process.env.NODE_ENV === "development") {
  const { worker } = require("./mocks/browser");
  worker.start();
}

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
