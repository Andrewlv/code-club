package com.codeclub.subject.domain.handler.subject;

import com.codeclub.subject.common.enums.SubjectInfoTypeEnum;
import com.codeclub.subject.domain.entity.SubjectInfoBO;

/**
 * 简答题目策略类
 */
public class BriefTypeHandler implements SubjectTypeHandler {
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
    }
}
