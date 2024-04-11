package com.codeclub.subject.domain.convert;

import com.codeclub.subject.domain.entity.SubjectInfoBO;
import com.codeclub.subject.infra.basic.entity.SubjectBrief;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BriefSubjectConverter {

    BriefSubjectConverter INSTANCE = Mappers.getMapper(BriefSubjectConverter.class);

    SubjectBrief convertBOToEntity(SubjectInfoBO subjectInfoBO);
}
