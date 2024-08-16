package com.codeclub.subject.domain.convert;

import com.codeclub.subject.domain.entity.SubjectInfoBO;
import com.codeclub.subject.domain.entity.SubjectLikedBO;
import com.codeclub.subject.infra.basic.entity.SubjectLiked;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 *  bo转换器
 *
 * @author Andrewlv
 * @since 2024-07-25 12:21:53
 */
@Mapper
public interface SubjectLikedBOConverter {

    SubjectLikedBOConverter INSTANCE = Mappers.getMapper(SubjectLikedBOConverter.class);

    SubjectLiked convertBOToEntity(SubjectLikedBO subjectLikedBO);

    List<SubjectLikedBO> convertInfoListToBO(List<SubjectLiked> subjectLikedList);
}
