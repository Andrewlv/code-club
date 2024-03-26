package com.codeclub.subject.domain.handler.subject;

import com.codeclub.subject.common.enums.SubjectInfoTypeEnum;
import com.codeclub.subject.domain.entity.SubjectInfoBO;

/**
 * 多选题目策略类
 */
public class MultipleTypeHandler implements SubjectTypeHandler {
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {

        // 单选题目的插入
    }
}
