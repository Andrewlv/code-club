package com.codeclub.subject.domain.convert;

import com.codeclub.subject.domain.entity.SubjectAnswerBO;
import com.codeclub.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RadioSubjectConverter {

    RadioSubjectConverter INSTANCE = Mappers.getMapper(RadioSubjectConverter.class);

    SubjectRadio convertBOToEntity(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertEntityToBOList(List<SubjectRadio> subjectRadioList);
}
