package com.codeclub.subject.application.convert;

import com.codeclub.subject.application.dto.SubjectCategoryDTO;
import com.codeclub.subject.application.dto.SubjectInfoDTO;
import com.codeclub.subject.domain.entity.SubjectCategoryBO;
import com.codeclub.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoDTOConverter {

    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBO convertDTOToBO(SubjectInfoDTO subjectInfoDTO);
}
