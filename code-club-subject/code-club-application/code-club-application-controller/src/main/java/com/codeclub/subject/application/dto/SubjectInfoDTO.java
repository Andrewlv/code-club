package com.codeclub.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目dto
 */
@Data
public class SubjectInfoDTO implements Serializable {
    private static final long serialVersionUID = -97158986872612313L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;

    /**
     * 分类id
     */
    List<Integer> categoryIds;

    /**
     * 标签id
     */
    List<Integer> labelIds;

    /**
     * 答案选项
     */
    private List<SubjectAnswerDTO> optionList;
}

