package com.codeclub.subject.infra.basic.service;

import com.codeclub.subject.common.entity.PageResult;
import com.codeclub.subject.infra.basic.entity.SubjectInfoEs;

public interface SubjectEsService {

    boolean insert(SubjectInfoEs subjectInfoEs);

    PageResult<SubjectInfoEs> querySubjectList(SubjectInfoEs subjectInfoEs);
}
