package com.codeclub.subject.domain.convert;

import com.codeclub.subject.domain.entity.SubjectCategoryBO;
import com.codeclub.subject.domain.entity.SubjectLabelBO;
import com.codeclub.subject.infra.basic.entity.SubjectCategory;
import com.codeclub.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelConverter {

    SubjectLabelConverter INSTANCE = Mappers.getMapper(SubjectLabelConverter.class);

    SubjectLabel convertBoToLabel(SubjectLabelBO subjectLabelBO);
}
