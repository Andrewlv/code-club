package com.codeclub.subject.domain.service;


import com.codeclub.subject.common.entity.PageResult;
import com.codeclub.subject.domain.entity.SubjectInfoBO;
import com.codeclub.subject.domain.entity.SubjectLikedBO;

/**
 * 领域service
 *
 * @author Andrewlv
 * @since 2024-07-25 12:21:53
 */
public interface SubjectLikedDomainService {

    /**
     * 添加  信息
     */
    void add(SubjectLikedBO subjectLikedBO);

    /**
     * 获取当前是否被点赞过
     *
     * @param subjectId
     * @param userId
     * @return
     */
    Boolean isLiked(String subjectId, String userId);

    /**
     * 获取题目被点赞数量
     *
     * @param subjectId
     * @return
     */
    Integer getLikedCount(String subjectId);

    /**
     * 更新  信息
     */
    Boolean update(SubjectLikedBO subjectLikedBO);

    /**
     * 删除  信息
     */
    Boolean delete(SubjectLikedBO subjectLikedBO);

    /**
     * 同步点赞数据
     */
    void syncLiked();

    PageResult<SubjectLikedBO> getSubjectLikedPage(SubjectLikedBO subjectLikedBO);
}
