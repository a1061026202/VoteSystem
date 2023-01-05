import cookies from 'js-cookie'

const TokenKey = 'token'
const username = 'user'

// token设置
export function getToken(){
    return cookies.get(TokenKey)
}

export function setToken(token){
    return cookies.set(TokenKey,token)
}

export function removeToken(){
    return cookies.remove(TokenKey) && cookies.remove(username)
}
// User设置
export function getUser(){
    return cookies.get(username)
}

export function setUser(user){
    return cookies.set(username,user)
}