{
  "name": "${projectName}",
  "version": "0.1.0",
  "private": true,
  "scripts": {
    "serve": "npx msw init public && vue-cli-service serve",
    "build": "vue-cli-service build",
    "test:unit": "vue-cli-service test:unit",
    "test:e2e": "vue-cli-service test:e2e",
    "lint": "vue-cli-service lint --fix",
    "doc": "make doc",
    "mock": "make mock"
  },
  "dependencies": {
    "ant-design-vue": "1.6.5",
    "axios": "0.21.0",
    "core-js": "3.6.5",
    "msw": "0.22.3",
    "vue": "2.6.11",
    "vue-router": "3.2.0",
    "vue2-waterfall": "3.0.1",
    "vuex": "3.4.0"
  },
  "devDependencies": {
    "@ant-design/colors": "3.2.1",
    "@babel/plugin-transform-runtime": "7.10.3",
    "@babel/preset-env": "7.10.3",
    "@types/jest": "24.0.19",
    "@typescript-eslint/eslint-plugin": "4.7.0",
    "@typescript-eslint/parser": "4.7.0",
    "@vue/cli-plugin-babel": "4.5.0",
    "@vue/cli-plugin-eslint": "4.5.8",
    "@vue/cli-plugin-router": "4.5.0",
    "@vue/cli-plugin-typescript": "4.5.0",
    "@vue/cli-plugin-unit-jest": "4.5.0",
    "@vue/cli-plugin-vuex": "4.5.0",
    "@vue/cli-service": "4.5.0",
    "@vue/eslint-config-airbnb": "5.1.0",
    "@vue/eslint-config-typescript": "7.0.0",
    "@vue/test-utils": "1.0.3",
    "babel-core": "7.0.0-bridge.0",
    "babel-plugin-import": "1.12.2",
    "babel-plugin-transform-remove-console": "6.9.4",
    "eslint": "7.0.0",
    "eslint-plugin-import": "2.20.2",
    "eslint-plugin-vue": "7.0.0",
    "jest": "24.5.0",
    "jquery": "3.5.1",
    "less": "3.12.2",
    "less-loader": "5.0.0",
    "script-loader": "0.7.2",
    "surge": "0.20.3",
    "ts-jest": "24.0.0",
    "typescript": "3.9.3",
    "vue-jest": "3.0.4",
    "vue-svg-icon-loader": "2.1.1",
    "vue-template-compiler": "2.6.11",
    "webpack-theme-color-replacer": "1.2.17"
  },
  "browserslist": [
    "> 1%",
    "last 2 versions",
    "not dead"
  ],
  "jest": {
    "preset": "@vue/cli-plugin-unit-jest/presets/typescript-and-babel"
  }
}
