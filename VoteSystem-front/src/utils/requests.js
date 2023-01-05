import axios from "axios";
import { getToken } from "./auth";


const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_API,
    timeout: 5000,
    headers:{'Content-Type':'application/json;charset=utf-8'}
})

//请求拦截器设置
service.interceptors.request.use(
config=>{
    let token = getToken()
    if(token != null){
        config.headers['Token'] = token
    }
    return config
},
error=>{
    console.log(error)
    return Promise.reject(error)
}
)

//响应拦截器

service.interceptors.response.use(
    response =>{
        const res = response.data
        console.log(res)
        return res
    },

    error=>{
        console.log(error)
        return Promise.reject(error)
    }
)


export default service