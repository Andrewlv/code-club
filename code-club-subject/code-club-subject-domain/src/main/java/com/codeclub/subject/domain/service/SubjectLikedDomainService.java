package com.codeclub.subject.domain.service;


import com.codeclub.subject.domain.entity.SubjectLikedBO;

/**
 *  领域service
 *
 * @author Andrewlv
 * @since 2024-07-25 12:21:53
 */
public interface SubjectLikedDomainService {

    /**
     * 添加  信息
     */
    Boolean add(SubjectLikedBO subjectLikedBO);

    /**
     * 更新  信息
     */
    Boolean update(SubjectLikedBO subjectLikedBO);

    /**
     * 删除  信息
     */
    Boolean delete(SubjectLikedBO subjectLikedBO);

}
