package com.ccghhll.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ccghhl
 * @since 2022-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="StateTable对象", description="")
public class StateTable implements Serializable {

    private static final long serialVersionUID = 1L;

    //用户id
    private String userName;

    //题目id
    private String topicId;

    //投票状态
    private Integer state;
}
