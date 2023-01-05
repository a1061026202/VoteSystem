package com.ccghhll.service;

import com.ccghhll.pojo.entity.OptionTable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ccghhl
 * @since 2022-09-06
 */
public interface OptionTableService extends IService<OptionTable> {
    //保存题目选项
    void saveOption(String uuid, String item);

    //根据题目id查询题目选项
    List getVoteOptionList(String id);

    //投票
    void voteOption(Integer id,String username,String topicId);

    List<OptionTable> getVoteResult(String topicId);

    //查询选项 依据选项人数排列
    List<OptionTable> getMostOption();
}
