package com.codeclub.subject.domain.convert;

import com.codeclub.subject.domain.entity.SubjectInfoBO;
import com.codeclub.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBOToInfo(SubjectInfoBO subjectInfoBO);
}
