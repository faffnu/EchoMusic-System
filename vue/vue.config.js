const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  //关闭全屏错误提示
  devServer: {
    client: {
      overlay: false,
    },
  }
})
