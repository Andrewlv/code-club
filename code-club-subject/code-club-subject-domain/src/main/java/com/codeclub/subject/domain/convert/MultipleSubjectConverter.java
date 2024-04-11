package com.codeclub.subject.domain.convert;

import com.codeclub.subject.domain.entity.SubjectAnswerBO;
import com.codeclub.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MultipleSubjectConverter {

    MultipleSubjectConverter INSTANCE = Mappers.getMapper(MultipleSubjectConverter.class);

    SubjectMultiple convertBOToEntity(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertEntityToBOList(List<SubjectMultiple> subjectMultipleList);
}
