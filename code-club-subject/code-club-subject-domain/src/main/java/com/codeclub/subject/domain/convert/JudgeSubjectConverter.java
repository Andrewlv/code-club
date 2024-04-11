package com.codeclub.subject.domain.convert;

import com.codeclub.subject.domain.entity.SubjectAnswerBO;
import com.codeclub.subject.infra.basic.entity.SubjectJudge;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface JudgeSubjectConverter {

    JudgeSubjectConverter INSTANCE = Mappers.getMapper(JudgeSubjectConverter.class);

    List<SubjectAnswerBO> convertEntityToBOList(List<SubjectJudge> subjectJudgeList);
}
