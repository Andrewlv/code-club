package com.codeclub.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目分类(SubjectCategory)实体类
 */
@Data
public class SubjectCategoryBO implements Serializable {
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
    /**
     * 数量
     */
    private Integer count;
}

