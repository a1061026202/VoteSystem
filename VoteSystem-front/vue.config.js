const path = require('path')


function resolve(dir) {
  return path.join(__dirname, dir)
}

// vue.config.js


module.exports = {
  // 选项...
  publicPath: './',
  lintOnSave: false, // 关闭Eslint语法检查
  configureWebpack: {
    resolve: {
      alias: {
        '@': resolve('src'),
      },
    },
    externals:{
      'vue': 'Vue',
      'element-ui': 'ELEMENT',
      'vuex': 'Vuex',
      'vue-router': 'VueRouter',
      'nprogress': 'NProgress',
      'axios': 'axios'
    }
  }
}