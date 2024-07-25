package com.codeclub.subject.domain.convert;

import com.codeclub.subject.domain.entity.SubjectLikedBO;
import com.codeclub.subject.infra.basic.entity.SubjectLiked;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-25T16:35:50+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_352 (Amazon.com Inc.)"
)
public class SubjectLikedBOConverterImpl implements SubjectLikedBOConverter {

    @Override
    public SubjectLiked convertBOToEntity(SubjectLikedBO subjectLikedBO) {
        if ( subjectLikedBO == null ) {
            return null;
        }

        SubjectLiked subjectLiked = new SubjectLiked();

        subjectLiked.setId( subjectLikedBO.getId() );
        subjectLiked.setSubjectId( subjectLikedBO.getSubjectId() );
        subjectLiked.setLikeUserId( subjectLikedBO.getLikeUserId() );
        subjectLiked.setStatus( subjectLikedBO.getStatus() );
        subjectLiked.setCreatedBy( subjectLikedBO.getCreatedBy() );
        subjectLiked.setCreatedTime( subjectLikedBO.getCreatedTime() );
        subjectLiked.setUpdateBy( subjectLikedBO.getUpdateBy() );
        subjectLiked.setUpdateTime( subjectLikedBO.getUpdateTime() );
        subjectLiked.setIsDeleted( subjectLikedBO.getIsDeleted() );

        return subjectLiked;
    }
}
