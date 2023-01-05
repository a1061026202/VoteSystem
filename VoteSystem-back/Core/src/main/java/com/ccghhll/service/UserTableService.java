package com.ccghhll.service;

import com.ccghhll.pojo.entity.UserTable;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ccghhll.vo.loginVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ccghhl
 * @since 2022-09-06
 */
public interface UserTableService extends IService<UserTable> {
    //登录功能
    Map<String,Object> login(loginVo loginvo);

    //注册功能
    void regist(loginVo loginvo);
}
