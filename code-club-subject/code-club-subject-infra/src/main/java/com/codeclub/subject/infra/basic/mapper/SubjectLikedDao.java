package com.codeclub.subject.infra.basic.mapper;

import com.codeclub.subject.infra.basic.entity.SubjectLiked;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  表数据库访问层
 *
 * @author Andrewlv
 * @since 2024-07-25 12:21:53
 */
@Repository
public interface SubjectLikedDao extends BaseMapper<SubjectLiked> {

}

