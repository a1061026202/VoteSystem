package com.ccghhll.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccghhll.pojo.entity.TopicTable;
import com.ccghhll.vo.topicVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ccghhl
 * @since 2022-09-06
 */
public interface TopicTableService extends IService<TopicTable> {

    //查询所有题目返回list集合
    List<TopicTable> getVoteTopicList();

    //保存投票题目和选项
    void saveTopic(String account ,topicVo topicVo);
    //删除话题
    void deleteTopic(String topicId);
    //获取该用户创建的话题
    List<TopicTable> getTopicByUser(String userName);

}
