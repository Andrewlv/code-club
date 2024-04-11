package com.codeclub.subject.domain.service;

import com.codeclub.subject.common.entity.PageResult;
import com.codeclub.subject.domain.entity.SubjectInfoBO;

/**
 * 题目领域服务
 */
public interface SubjectInfoDomainService {

    /**
     * 新增题目
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 分页查询题目列表
     *
     * @param subjectInfoBO
     * @return
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    /**
     * 查询题目信息
     *
     * @param subjectInfoBO
     * @return
     */
    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);
}
