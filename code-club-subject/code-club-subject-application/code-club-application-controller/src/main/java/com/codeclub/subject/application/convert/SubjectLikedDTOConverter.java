package com.codeclub.subject.application.convert;

import com.codeclub.subject.application.dto.SubjectLikedDTO;
import com.codeclub.subject.domain.entity.SubjectLikedBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *  dto转换器
 *
 * @author Andrewlv
 * @since 2024-07-25 12:21:53
 */
@Mapper
public interface SubjectLikedDTOConverter {

    SubjectLikedDTOConverter INSTANCE = Mappers.getMapper(SubjectLikedDTOConverter.class);

    SubjectLikedBO convertDTOToBO(SubjectLikedDTO subjectLikedDTO);

}
