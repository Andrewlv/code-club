package com.codeclub.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.codeclub.subject.common.entity.PageResult;
import com.codeclub.subject.common.enums.IsDeletedFlagEnum;
import com.codeclub.subject.common.enums.SubjectLikedStatusEnum;
import com.codeclub.subject.common.util.LoginUtil;
import com.codeclub.subject.domain.convert.SubjectLikedBOConverter;
import com.codeclub.subject.domain.entity.SubjectInfoBO;
import com.codeclub.subject.domain.entity.SubjectLikedBO;
import com.codeclub.subject.domain.redis.RedisUtil;
import com.codeclub.subject.domain.service.SubjectLikedDomainService;

import com.codeclub.subject.infra.basic.entity.SubjectInfo;
import com.codeclub.subject.infra.basic.entity.SubjectLiked;

import com.codeclub.subject.infra.basic.service.SubjectInfoService;
import com.codeclub.subject.infra.basic.service.SubjectLikedService;
import com.codeclub.subject.infra.basic.service.impl.SubjectInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 领域service实现类
 *
 * @author Andrewlv
 * @since 2024-07-25 12:21:53
 */
@Service
@Slf4j
public class SubjectLikedDomainServiceImpl implements SubjectLikedDomainService {

    @Resource
    private SubjectLikedService subjectLikedService;

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private RedisUtil redisUtil;

    private static final String SUBJECT_LIKED_KEY = "subject.liked";

    private static final String SUBJECT_LIKED_COUNT_KEY = "subject.liked.count";

    private static final String SUBJECT_LIKED_DETAIL_KEY = "subject.liked.detail";


    @Override
    public void add(SubjectLikedBO subjectLikedBO) {
        Long subjectId = subjectLikedBO.getSubjectId();
        String likeUserId = subjectLikedBO.getLikeUserId();
        Integer status = subjectLikedBO.getStatus();
        String hashKey = buildSubjectLikedKey(subjectId.toString(), likeUserId);
        redisUtil.putHash(SUBJECT_LIKED_KEY, hashKey, status);
        String countKey = SUBJECT_LIKED_COUNT_KEY + "." + subjectId;
        String detailKey = SUBJECT_LIKED_DETAIL_KEY + "." + subjectId + "." + likeUserId;
        if (SubjectLikedStatusEnum.LIKED.getCode() == status) {
            redisUtil.increment(countKey, 1);
            redisUtil.set(detailKey, "1");
        } else {
            Integer count = redisUtil.getInt(countKey);
            if (Objects.isNull(count) || count <= 0) {
                return;
            }
            redisUtil.increment(countKey, -1);
            redisUtil.del(detailKey);
        }
    }

    @Override
    public Boolean isLiked(String subjectId, String userId) {
        String detailKey = SUBJECT_LIKED_DETAIL_KEY + "." + subjectId + "." + userId;
        return redisUtil.exist(detailKey);
    }

    @Override
    public Integer getLikedCount(String subjectId) {
        String countKey = SUBJECT_LIKED_COUNT_KEY + "." + subjectId;
        Integer count = redisUtil.getInt(countKey);
        if (Objects.isNull(count) || count <= 0) {
            return 0;
        }
        return count;
    }

    public String buildSubjectLikedKey(String subjectId, String userId) {
        return subjectId + ":" + userId;
    }

    @Override
    public Boolean update(SubjectLikedBO subjectLikedBO) {
        SubjectLiked subjectLiked = SubjectLikedBOConverter.INSTANCE.convertBOToEntity(subjectLikedBO);
        return subjectLikedService.update(subjectLiked) > 0;
    }

    @Override
    public Boolean delete(SubjectLikedBO subjectLikedBO) {
        SubjectLiked subjectLiked = new SubjectLiked();
        subjectLiked.setId(subjectLikedBO.getId());
        subjectLiked.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return subjectLikedService.update(subjectLiked) > 0;
    }

    @Override
    public void syncLiked() {
        Map<Object, Object> subjectLikedMap = redisUtil.getHashAndDelete(SUBJECT_LIKED_KEY);
        if (log.isInfoEnabled()) {
            log.info("syncLiked.subjectLikedMap:{}", JSON.toJSONString(subjectLikedMap));
        }
        if (MapUtils.isEmpty(subjectLikedMap)) {
            return;
        }
        // 批量同步到数据库
        List<SubjectLiked> subjectLikedList = new ArrayList<>();
        subjectLikedMap.forEach((key, value) -> {
            SubjectLiked subjectLiked = new SubjectLiked();
            String[] keyArr = key.toString().split(":");
            String subjectId = keyArr[0];
            String likedUser = keyArr[1];
            subjectLiked.setSubjectId(Long.valueOf(subjectId));
            subjectLiked.setLikeUserId(likedUser);
            subjectLiked.setStatus(Integer.valueOf(value.toString()));
            subjectLiked.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectLikedList.add(subjectLiked);
        });
        subjectLikedService.batchInsertOrUpdate(subjectLikedList);
    }

    @Override
    public PageResult<SubjectLikedBO> getSubjectLikedPage(SubjectLikedBO subjectLikedBO) {
        PageResult<SubjectLikedBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectLikedBO.getPageNo());
        pageResult.setPageSize(subjectLikedBO.getPageSize());
        int start = (subjectLikedBO.getPageNo() - 1) * subjectLikedBO.getPageSize();
        SubjectLiked subjectLiked = SubjectLikedBOConverter.INSTANCE.convertBOToEntity(subjectLikedBO);
        subjectLiked.setLikeUserId(LoginUtil.getLoginId());
        int count = subjectLikedService.countByCondition(subjectLiked);
        if (count == 0) {
            return pageResult;
        }
        List<SubjectLiked> subjectLikedList = subjectLikedService.queryPage(subjectLiked, start, subjectLikedBO.getPageSize());
        List<SubjectLikedBO> subjectLikedBOS = SubjectLikedBOConverter.INSTANCE.convertInfoListToBO(subjectLikedList);
        subjectLikedBOS.forEach(liked ->{
            SubjectInfo subjectInfo = subjectInfoService.queryById(liked.getSubjectId());
            liked.setSubjectName(subjectInfo.getSubjectName());
        });
        pageResult.setRecords(subjectLikedBOS);
        pageResult.setTotal(count);
        return pageResult;
    }

}
