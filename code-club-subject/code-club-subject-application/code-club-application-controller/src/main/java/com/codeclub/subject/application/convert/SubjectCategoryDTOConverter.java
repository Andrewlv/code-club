package com.codeclub.subject.application.convert;

import com.codeclub.subject.application.dto.SubjectCategoryDTO;
import com.codeclub.subject.domain.entity.SubjectCategoryBO;
import com.codeclub.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryDTOConverter {

    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryDTO convertBoToDTO(SubjectCategoryBO subjectCategoryBO);

    SubjectCategoryBO convertDTOToBO(SubjectCategoryDTO subjectCategoryDTO);

    List<SubjectCategoryDTO> convertBoListToDTOList(List<SubjectCategoryBO> subjectCategoryBOList);
}
