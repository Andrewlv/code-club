package com.codeclub.subject.domain.handler.subject;

import com.codeclub.subject.common.enums.SubjectInfoTypeEnum;
import com.codeclub.subject.domain.entity.SubjectInfoBO;

public interface SubjectTypeHandler {

    /**
     * 枚举身份识别
     *
     * @return
     */
    SubjectInfoTypeEnum getHandlerType();

    /**
     * 实际的题目插入方法
     *
     * @param subjectInfoBO
     */
    void add(SubjectInfoBO subjectInfoBO);
}
