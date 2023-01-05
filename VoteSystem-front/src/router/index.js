import Vue from 'vue'
import VueRouter from 'vue-router'
import NProgress from 'nprogress'
// import 'nprogress/nprogress.css'
import Home from '../views/Home.vue'

const auth = require('../utils/auth')
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/votelist',
    name: 'Votelist',
    component: ()=>import('../components/main/ToDo/ToVote.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 进度条设置
NProgress.configure({
  easing: 'ease',  // 动画方式
  speed: 500,  // 递增进度条的速度
  showSpinner: false, // 是否显示加载ico
  trickleSpeed: 200, // 自动递增间隔
  minimum: 0.3 // 初始化时的最小百分比
})

// 守护路由
router.beforeEach((to, from,next) => {
  NProgress.start()
  // 跳过无权限的路径
  if(to.name !== 'Votelist'){
    next()
    // 判断是否有cookie,有则允许访问
  }else if(auth.getToken() == undefined){
    next({name: 'Home'})
    alert("非法访问,请先登陆账户")
  }else{
    next()
  }
})
router.afterEach(() => {
  NProgress.done()
})

export default router
