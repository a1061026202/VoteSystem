package com.ccghhll.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ResponseEnum {
    SUCCESS(1,"成功"),
    REEOR(-1,"服务器内部错误"),
    //-1xx 服务器错误
    BAD_SQL_GRAMMAR_ERROR(-101, "sql语法错误"),
    SERVLET_ERROR(-102, "servlet请求异常"), //-2xx 参数校验
    WEIXIN_FETCH_USERINFO_ERROR(-603, "获取用户信息失败");


    //响应状态码
    private Integer code;

    //响应信息
    private String message;
}
