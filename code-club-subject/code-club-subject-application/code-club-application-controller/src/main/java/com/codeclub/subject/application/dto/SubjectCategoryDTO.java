package com.codeclub.subject.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目分类(SubjectCategory)实体类
 *
 * @author makejava
 * @since 2024-03-18 17:46:23
 */
@Data
public class SubjectCategoryDTO implements Serializable {
    private static final long serialVersionUID = -75371079816246164L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类类型
     */
    private Integer categoryType;
    /**
     * 图标连接
     */
    private String imageUrl;
    /**
     * 父级id
     */
    private Long parentId;


}

