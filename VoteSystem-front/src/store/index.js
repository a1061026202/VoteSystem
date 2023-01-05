import Vue from 'vue'
import Vuex from 'vuex'
const auth = require('../utils/auth')

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    // 判断用户是否登陆
    loginflag: false,
    // 获取登陆用户名
    User: ''
  },
  getters: {
    // 获取state值
    getloginflag(state){
      return state.loginflag
    },
    // 获取用户名
    getUsername(state){
      return state.User
    }
  },
  actions: {
    // 修改状态
    tologinflag(context){
      if(auth.getToken() != null){
      context.state.loginflag = true
    }else{
      context.state.loginflag = false
    }
    },
    // 获取用户
    toUser(context){
      if(auth.getToken() != null){
        context.state.User = auth.getUser()
      }
    }
  }
})
