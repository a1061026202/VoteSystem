package com.ccghhll.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccghhll.pojo.entity.UserTable;
import org.apache.ibatis.annotations.Mapper;

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
public interface UserTableMapper extends BaseMapper<UserTable> {
    List<UserTable> getAllUser();

}
