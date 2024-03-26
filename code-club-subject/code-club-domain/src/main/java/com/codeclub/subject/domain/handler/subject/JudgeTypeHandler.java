package com.codeclub.subject.domain.handler.subject;

import com.codeclub.subject.common.enums.SubjectInfoTypeEnum;
import com.codeclub.subject.domain.entity.SubjectInfoBO;

/**
 * 判断题目策略类
 */
public class JudgeTypeHandler implements SubjectTypeHandler {
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
    }
}
