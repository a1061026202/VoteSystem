package com.ccghhll.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccghhll.exception.Assert;
import com.ccghhll.mapper.TopicTableMapper;
import com.ccghhll.pojo.entity.TopicTable;
import com.ccghhll.result.ResponseEnum;
import com.ccghhll.service.OptionTableService;
import com.ccghhll.service.TopicTableService;
import com.ccghhll.vo.msgVo;
import com.ccghhll.vo.topicVo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ccghhl
 * @since 2022-09-06
 */
@Service
public class TopicTableServiceImpl extends ServiceImpl<TopicTableMapper, TopicTable> implements TopicTableService {

    @Autowired
    OptionTableService optionTableService;

    @Override
    public List<TopicTable> getVoteTopicList() {
        List<TopicTable> topicList = baseMapper.selectList(null);
        return topicList;
    }

    @Override
    public void saveTopic(String account,topicVo topicVo) {
        Assert.notEmpty(topicVo.getTitle(), ResponseEnum.REEOR);
        //录入标题
        String title = topicVo.getTitle();
        //截取uuid后11位
        String uuid = UUID.randomUUID().toString().substring(25);
        baseMapper.insert(new TopicTable(uuid,title,account));
        //输入题目选项
        List<msgVo> options = topicVo.getOptions();
        options.forEach(item -> {
            optionTableService.saveOption(uuid,item.getMsg());
        });
    }
    //删除话题
    @Override
    public void deleteTopic(String topicId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("topic_id",topicId);
        Integer test = baseMapper.delete(queryWrapper);
        System.out.println(test);
    }

    @Override
    public List<TopicTable> getTopicByUser(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",userName);
        List topiclist = baseMapper.selectList(queryWrapper);
        return topiclist;
    }
}
