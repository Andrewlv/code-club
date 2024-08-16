package com.codeclub.subject.domain.entity;

import com.codeclub.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  bo
 *
 * @author Andrewlv
 * @since 2024-07-25 12:21:53
 */
@Data
public class SubjectLikedBO extends PageInfo implements Serializable {

    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private Long subjectId;

    /**
     * 
     */
    private String likeUserId;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private String createdBy;

    /**
     * 
     */
    private Date createdTime;

    /**
     * 
     */
    private String updateBy;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer isDeleted;

    private String subjectName;

}

