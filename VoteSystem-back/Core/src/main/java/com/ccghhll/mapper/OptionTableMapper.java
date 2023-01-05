package com.ccghhll.mapper;

import com.ccghhll.pojo.entity.OptionTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ccghhl
 * @since 2022-09-06
 */
@Mapper
public interface OptionTableMapper extends BaseMapper<OptionTable> {

    @Select("SELECT SUM(option_sum) FROM option_table GROUP BY topic_id;")
    List<Integer> opsumByTopic();

}
