package com.codeclub.subject.domain.convert;

import com.codeclub.subject.domain.entity.SubjectInfoBO;
import com.codeclub.subject.domain.entity.SubjectOptionBO;
import com.codeclub.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBOToInfo(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoBO> convertInfoListToBO(List<SubjectInfo> subjectInfoList);

    SubjectInfoBO convertOptionAndInfoToBO(SubjectOptionBO subjectOptionBO, SubjectInfo subjectInfo);

    SubjectInfoBO convertOptionToBO(SubjectOptionBO subjectOptionBO);
}
