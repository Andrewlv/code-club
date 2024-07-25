package com.codeclub.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  dto
 *
 * @author Andrewlv
 * @since 2024-07-25 12:21:53
 */
@Data
public class SubjectLikedDTO implements Serializable {

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

}

