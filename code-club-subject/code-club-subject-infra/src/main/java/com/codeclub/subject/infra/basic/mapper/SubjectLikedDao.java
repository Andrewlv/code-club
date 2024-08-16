package com.codeclub.subject.infra.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codeclub.subject.infra.basic.entity.SubjectLiked;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 表数据库访问层
 *
 * @author Andrewlv
 * @since 2024-07-25 12:21:53
 */
@Repository
public interface SubjectLikedDao extends BaseMapper<SubjectLiked> {

    void batchInsertOrUpdate(@Param("entities") List<SubjectLiked> subjectLikedList);

    int countByCondition(SubjectLiked subjectLiked);

    List<SubjectLiked> queryPage(@Param("entity") SubjectLiked subjectLiked,
                                 @Param("start") int start,
                                 @Param("pageSize") Integer pageSize);
}

