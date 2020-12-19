module.exports = {
  extends: [
    'plugin:vue/essential',
    '@vue/typescript/recommended',
  ],
  rules: {
    // override/add rules settings here, such as:
    'vue/no-unused-vars': 'error',
    "vue/no-unused-properties": ["error", {
      "groups": ["props", "data", "computed", "methods", "setup"]
    }],
    "@typescript-eslint/explicit-module-boundary-types": "off",
    "@typescript-eslint/ban-ts-comment": "off",
    "quotes": "off",
    "@typescript-eslint/no-var-requires": "off"
  }
}
