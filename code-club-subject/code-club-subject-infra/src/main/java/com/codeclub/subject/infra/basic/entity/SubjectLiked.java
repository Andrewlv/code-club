package com.codeclub.subject.infra.basic.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 *  实体类
 *
 * @author Andrewlv
 * @since 2024-07-25 12:21:53
 */
@Data
@TableName("subject_liked")
public class SubjectLiked implements Serializable {

    /**
     * 
     */
    @TableId(value = "`id`", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField("`subject_id`")
    private Long subjectId;

    /**
     * 
     */
    @TableField("`like_user_id`")
    private String likeUserId;

    /**
     * 
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 
     */
    @TableField("`created_by`")
    private String createdBy;

    /**
     * 
     */
    @TableField("`created_time`")
    private Date createdTime;

    /**
     * 
     */
    @TableField("`update_by`")
    private String updateBy;

    /**
     * 
     */
    @TableField("`update_time`")
    private Date updateTime;

    /**
     * 
     */
    @TableField("`is_deleted`")
    private Integer isDeleted;

}

