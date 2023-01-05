import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
// import { Message } from 'element-ui'
// import 'element-ui/lib/theme-chalk/index.css'
// 全局样式
import './assets/style/global.css'

Vue.use(ElementUI)

Vue.config.productionTip = false
//Vue.prototype.$message = Message

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
