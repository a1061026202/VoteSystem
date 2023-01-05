package com.ccghhll.service;

import com.ccghhll.pojo.entity.StateTable;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ccghhl
 * @since 2022-09-06
 */
public interface StateTableService extends IService<StateTable> {

    //判断用户是否已投票
    boolean isVote(String username, String topicId);

    //设置用户已经投票
    boolean Voted(String topicId,String username);
}
