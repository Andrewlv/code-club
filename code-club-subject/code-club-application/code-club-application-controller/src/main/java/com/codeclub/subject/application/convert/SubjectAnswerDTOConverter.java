package com.codeclub.subject.application.convert;

import com.codeclub.subject.application.dto.SubjectAnswerDTO;
import com.codeclub.subject.application.dto.SubjectInfoDTO;
import com.codeclub.subject.domain.entity.SubjectAnswerBO;
import com.codeclub.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerDTOConverter {

    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);

    List<SubjectAnswerBO> convertListDTOToBO(List<SubjectAnswerDTO> subjectAnswerDTOS);
}
