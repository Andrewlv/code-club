package com.codeclub.subject.application.convert;

import com.codeclub.subject.application.dto.SubjectCategoryDTO;
import com.codeclub.subject.application.dto.SubjectLabelDTO;
import com.codeclub.subject.domain.entity.SubjectCategoryBO;
import com.codeclub.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelDTOConverter {

    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    SubjectLabelBO convertDtoToBo(SubjectLabelDTO subjectLabelDTO);

    List<SubjectLabelDTO> convertBOToLabelDTOList(List<SubjectLabelBO> subjectLabelBOS);
}
