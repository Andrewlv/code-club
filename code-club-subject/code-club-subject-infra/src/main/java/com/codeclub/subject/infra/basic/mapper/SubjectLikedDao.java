package com.codeclub.subject.infra.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codeclub.subject.infra.basic.entity.SubjectLiked;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 表数据库访问层
 *
 * @author Andrewlv
 * @since 2024-07-25 12:21:53
 */
public interface SubjectLikedDao extends BaseMapper<SubjectLiked> {

    void batchInsertOrUpdate(@Param("entities") List<SubjectLiked> subjectLikedList);
}

