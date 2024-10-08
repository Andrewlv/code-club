package com.codeclub.subject.application.dto;

import com.codeclub.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目dto
 */
@Data
public class SubjectInfoDTO extends PageInfo implements Serializable {
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
     * 题目答案
     */
    private String subjectAnswer;
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

    /**
     * 创建人昵称
     */
    private String createUser;

    /**
     * 创建人头像
     */
    private String createUserAvatar;

    /**
     * 题目数量
     */
    private Integer subjectCount;

    private Long categoryId;

    private Long labelId;

    private List<String> labelNameList;

    /**
     * 全文检索关键词
     */
    private String keyWord;

    /**
     * 当前题目是否被点赞
     */
    private Boolean liked;

    /**
     * 当前题目被点赞数量
     */
    private Integer liked_count;

    /**
     * 下一题id
     */
    private Long nextSubjectId;

    /**
     * 上一题id
     */
    private Long lastSubjectId;
}

