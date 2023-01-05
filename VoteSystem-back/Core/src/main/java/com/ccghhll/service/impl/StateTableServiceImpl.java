package com.ccghhll.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccghhll.mapper.OptionTableMapper;
import com.ccghhll.pojo.entity.StateTable;
import com.ccghhll.mapper.StateTableMapper;
import com.ccghhll.service.StateTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ccghhl
 * @since 2022-09-06
 */
@Service
@Slf4j
public class StateTableServiceImpl extends ServiceImpl<StateTableMapper, StateTable> implements StateTableService {
    @Autowired
    StateTableMapper mapper;
    //设置用户已投票
    @Override
    public boolean Voted(String topicId,String username) {
        QueryWrapper queryWrapper = new QueryWrapper<StateTable>();
        queryWrapper.eq("topic_id",topicId);
        queryWrapper.eq("user_name",username);
        StateTable stateTable = baseMapper.selectOne(queryWrapper);
        stateTable.setState(1);
        int i =baseMapper.update(stateTable,queryWrapper);
        if(i>0){ return true;}
        else {return false;}

    }

    //判断用户是否已投票
    @Override
    public boolean isVote(String username, String topicId) {
        QueryWrapper queryWrapper = new QueryWrapper<StateTable>();
        queryWrapper.eq("user_name",username);
        queryWrapper.eq("topic_id",topicId);
        Integer integer = baseMapper.selectCount(queryWrapper);
        System.out.println(integer);
        //设置state默认为false 没有投票状态
        if (integer > 0){
            queryWrapper.eq("state",1);
            //如果已经投票返回true
            Integer integer1 = baseMapper.selectCount(queryWrapper);
             System.out.println("integer"+integer1);
            if(integer1>0) {
                return true;
            }else {
                return false;
            }
        }else{
            baseMapper.insert(new StateTable(username,topicId,0));
            return false;
        }
    }




}
