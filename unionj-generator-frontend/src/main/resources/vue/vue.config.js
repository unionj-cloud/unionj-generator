const path = require("path");
const webpack = require("webpack");

function resolve(dir) {
  return path.join(__dirname, dir);
}

const isProd = process.env.NODE_ENV === "production";

const assetsCDN = {
  // webpack build externals
  externals: {
    vue: "Vue",
    "vue-router": "VueRouter",
    vuex: "Vuex",
    axios: "axios"
  }
};

// vue.config.js
const vueConfig = {
  configureWebpack: {
    // webpack plugins
    plugins: [
      // Ignore all locale files of moment.js
      new webpack.IgnorePlugin(/^\.\/locale$/, /moment$/)
    ],
    // if prod, add externals
    externals: isProd ? assetsCDN.externals : {}
  },

  chainWebpack: config => {
    config.resolve.alias.set("@$", resolve("src"));

    const svgRule = config.module.rule("svg");
    svgRule.uses.clear();
    svgRule
      .oneOf("inline")
      .resourceQuery(/inline/)
      .use("vue-svg-icon-loader")
      .loader("vue-svg-icon-loader")
      .end()
      .end()
      .oneOf("external")
      .use("file-loader")
      .loader("file-loader")
      .options({
        name: "assets/[name].[hash:8].[ext]"
      });

    config.plugin("html").tap(args => {
      // TODO
      args[0].title = "your title";
      return args;
    });
  },

  css: {
    loaderOptions: {
      less: {
        modifyVars: {
          // less vars，customize ant design theme

          // 'primary-color': '#F5222D',
          // 'link-color': '#F5222D',
          "border-radius-base": "2px",
          "menu-item-height": "64px",
          "menu-inline-toplevel-item-height": "64px"
        },
        // DO NOT REMOVE THIS LINE
        javascriptEnabled: true
      }
    }
  },

  devServer: {
    port: 8000,
    disableHostCheck: true,
    proxy: {
      "^/api": {
        target: process.env.VUE_APP_PROXY_TARGET,
        ws: true,
        changeOrigin: true,
        timeout: 1000 * 60 * 10,
        pathRewrite: {
          "^/api": "/"
        }
      }
    }
  },

  // disable source map in production
  productionSourceMap: false,
  lintOnSave: undefined,
  // babel-loader no-ignore node_modules/*
  transpileDependencies: []
};

module.exports = vueConfig;
