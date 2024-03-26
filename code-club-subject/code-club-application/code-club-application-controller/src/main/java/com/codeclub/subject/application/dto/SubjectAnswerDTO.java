package com.codeclub.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目答案dto
 */
@Data
public class SubjectAnswerDTO implements Serializable {
    private static final long serialVersionUID = -97158986872612313L;

    /**
     * 答案选项标识
     */
    private Integer optionType;

    /**
     * 答案内容
     */
    private String optionContent;

    /**
     * 是否正确
     */
    private Integer isCorrect;
}

