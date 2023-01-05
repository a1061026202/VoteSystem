package com.ccghhll.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccghhll.exception.BusinessException;
import com.ccghhll.mapper.UserTableMapper;
import com.ccghhll.pojo.entity.UserTable;
import com.ccghhll.service.UserTableService;
import com.ccghhll.utils.JwtCryptoUtil;
import com.ccghhll.vo.loginVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ccghhl
 * @since 2022-09-06
 */
@Service
public class UserTableServiceImpl extends ServiceImpl<UserTableMapper, UserTable> implements UserTableService {

    @Override
    public Map<String,Object> login(loginVo loginvo) {
        //提取用户名字和密码
        String userName = loginvo.getUserName();
        String password = loginvo.getPassword();
        //定义条件用了mybatisplus
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",userName);
        queryWrapper.eq("password",password);
        Integer count = baseMapper.selectCount(queryWrapper);
        //判断用户是否存在
        if(count == 0){
            throw new BusinessException("用户名不存在或密码错误",-1);
        }else{
            //根据用户名生成token
            String token = JwtCryptoUtil.createToken(userName);
            Map<String, Object> map = new HashMap<>();
            map.put("token",token);
            return map;
        }

    }

    @Override
    public void regist(loginVo loginvo) {
        String userName = loginvo.getUserName();
        String password = loginvo.getPassword();
        QueryWrapper<UserTable> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName);
        Integer count1 = baseMapper.selectCount(wrapper);
        if(count1 > 0) {
            throw new BusinessException("该用户已存在",-1);
        }
        UserTable user = new UserTable();
        user.setUserName(userName);
        user.setPassword(password);
        int count = baseMapper.insert(user);
        if(count == 0){
            throw new BusinessException("注册用户失败",-1);
        }
    }
}
