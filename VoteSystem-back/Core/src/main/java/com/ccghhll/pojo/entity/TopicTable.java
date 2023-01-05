package com.ccghhll.pojo.entity;

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
@ApiModel(value="TopicTable对象", description="")
@AllArgsConstructor
public class TopicTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "topic_id")
    private String topicId;

    private String topicName;

    private String userName;
}
