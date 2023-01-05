import request from '@/utils/requests.js'


export default{
    tologin(data){
        return request({
            url: '/api/vote/login',
            method: 'post',
            data: data
        })
    },

    toRegister(data){
        return request({
            url:'/api/vote/regist',
            method: 'post',
            data: {'userName': data.username, 'password': data.password}
        })
    }
}