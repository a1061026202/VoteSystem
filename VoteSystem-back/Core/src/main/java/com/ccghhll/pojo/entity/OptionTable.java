package com.ccghhll.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value="OptionTable对象", description="")
@AllArgsConstructor
public class OptionTable implements Serializable {

    private static final long serialVersionUID = 1L;
    //选项的id
    @TableId(value = "option_id", type = IdType.AUTO)
    private Integer optionId;
    //选项描述
    private String optionDec;
    //题目的id
    private String topicId;
    //选择数量
    private int optionSum;


}
