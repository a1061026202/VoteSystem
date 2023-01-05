package com.ccghhll.service.impl;

import cn.hutool.core.lang.Opt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccghhll.pojo.entity.OptionTable;
import com.ccghhll.mapper.OptionTableMapper;
import com.ccghhll.pojo.entity.TopicTable;
import com.ccghhll.service.OptionTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccghhll.service.StateTableService;
import com.ccghhll.service.TopicTableService;
import io.swagger.models.auth.In;
import javafx.beans.binding.MapBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.commons.lang3.ArrayUtils.toArray;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ccghhl
 * @since 2022-09-06
 */
@Service
public class OptionTableServiceImpl extends ServiceImpl<OptionTableMapper, OptionTable> implements OptionTableService {

    @Autowired
    private StateTableService stateTableService;
    @Autowired
    private TopicTableService topicTableService;


    //保存题目选项
    @Override
    public void saveOption(String uuid, String item) {
        baseMapper.insert(new OptionTable(null,item,uuid,0));
    }

    //根据题目id查询题目选项
    @Override
    public List<OptionTable> getVoteOptionList(String id) {
        //条件查询
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("topic_id",id);
        List<OptionTable> list = baseMapper.selectList(queryWrapper);
        return list;
    }

    //投票
    @Override
    public void voteOption(Integer id,String username,String topicId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("option_id",id);
        queryWrapper.eq("topic_id",topicId);
        OptionTable optionTable = baseMapper.selectOne(queryWrapper);
        Integer optionSum = optionTable.getOptionSum();
        optionSum = optionSum+1;
        optionTable.setOptionSum(optionSum);
        baseMapper.updateById(optionTable);
        stateTableService.Voted(topicId,username);
    }

    @Override
    public List<OptionTable> getVoteResult(String topicId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("topic_id",topicId);
        List<OptionTable> optionTableList = baseMapper.selectList(queryWrapper);
        return optionTableList;

    }

    @Override
    public List<OptionTable> getMostOption() {
        List<OptionTable> optionTableList = baseMapper.selectList(null);
        List<TopicTable> topicTableList = topicTableService.getBaseMapper().selectList(null);


        List<Integer> list = baseMapper.opsumByTopic();


        Collections.sort(list,Collections.reverseOrder());//list的降序排序

        Collections.sort(topicTableList, new Comparator<TopicTable>() {
            @Override
            public int compare(TopicTable o1, TopicTable o2) {
                int io1 = list.indexOf(o1);
                int io2 = list.indexOf(o2);

                return ;
            }
        });
        System.out.println(topicTableList);


        System.out.println(list);

//        Integer sum;
//
//        Integer[] opsum = new Integer[optionTableList.size()];
//
//        int[] opsumByTopic = new int[topicTableList.size()];
//
//        String[] topicIdbyOp = new String[optionTableList.size()];
//        int j =0;
//
//        for(int i = 0;i < optionTableList.size(); i ++){
//            OptionTable optionTable = optionTableList.get(i);
//            sum = optionTable.getOptionSum();
//            opsum[i] = sum;
//        }
//        int i =0;
//        while (i < optionTableList.size()&&j<topicTableList.size()&&i<optionTableList.size() ){//
//            OptionTable optionTable = optionTableList.get(i);
//            String topicId = optionTable.getTopicId();
//            System.out.println(topicId);
//            topicIdbyOp[i] =  topicId;
//            i ++;
//           if(topicIdbyOp[i] == topicIdbyOp[i+1]&&i<optionTableList.size()){
//               opsumByTopic[j] = opsum[i] + opsum[i+1];
//           }else{
//               opsumByTopic[j] = opsum[i];
//               opsumByTopic[j+1] = opsum[i+1];//
//               j++;
//           }
//        }
//
//        System.out.println(Arrays.toString(opsumByTopic));
//




//        Object[] optionlist;
//        optionlist = optionTableList.toArray();
//        System.out.println(Arrays.toString(optionlist));
        return optionTableList;
    }

}
