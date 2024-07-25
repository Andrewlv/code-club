package com.codeclub.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.codeclub.subject.application.convert.SubjectLikedDTOConverter;
import com.codeclub.subject.application.dto.SubjectLikedDTO;
import com.codeclub.subject.common.entity.Result;
import com.codeclub.subject.domain.entity.SubjectLikedBO;
import com.codeclub.subject.domain.service.SubjectLikedDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *  controller
 *
 * @author Andrewlv
 * @since 2024-07-25 12:21:53
 */
@RestController
@RequestMapping("/subjectLiked/")
@Slf4j
public class SubjectLikedController {

    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;

    /**
     * 新增
     */
    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody SubjectLikedDTO subjectLikedDTO) {

        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLikedController.add.dto:{}", JSON.toJSONString(subjectLikedDTO));
            }
            Preconditions.checkNotNull(subjectLikedDTO.getId(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getSubjectId(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getLikeUserId(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getStatus(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getCreatedBy(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getCreatedTime(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getUpdateBy(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getUpdateTime(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getIsDeleted(), "不能为空");
            SubjectLikedBO SubjectLikedBO = SubjectLikedDTOConverter.INSTANCE.convertDTOToBO(subjectLikedDTO);
            return Result.ok(subjectLikedDomainService.add(SubjectLikedBO));
        } catch (Exception e) {
            log.error("SubjectLikedController.register.error:{}", e.getMessage(), e);
            return Result.fail("新增失败");
        }

    }

    /**
     * 修改
     */
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody SubjectLikedDTO subjectLikedDTO) {

        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLikedController.update.dto:{}", JSON.toJSONString(subjectLikedDTO));
            }
            Preconditions.checkNotNull(subjectLikedDTO.getId(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getSubjectId(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getLikeUserId(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getStatus(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getCreatedBy(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getCreatedTime(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getUpdateBy(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getUpdateTime(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getIsDeleted(), "不能为空");
            SubjectLikedBO subjectLikedBO = SubjectLikedDTOConverter.INSTANCE.convertDTOToBO(subjectLikedDTO);
            return Result.ok(subjectLikedDomainService.update(subjectLikedBO));
        } catch (Exception e) {
            log.error("SubjectLikedController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新信息失败");
        }

    }

    /**
     * 删除
     */
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody SubjectLikedDTO subjectLikedDTO) {

        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLikedController.delete.dto:{}", JSON.toJSONString(subjectLikedDTO));
            }
            Preconditions.checkNotNull(subjectLikedDTO.getId(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getSubjectId(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getLikeUserId(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getStatus(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getCreatedBy(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getCreatedTime(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getUpdateBy(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getUpdateTime(), "不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getIsDeleted(), "不能为空");
            SubjectLikedBO subjectLikedBO = SubjectLikedDTOConverter.INSTANCE.convertDTOToBO(subjectLikedDTO);
            return Result.ok(subjectLikedDomainService.delete(subjectLikedBO));
        } catch (Exception e) {
            log.error("SubjectLikedController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除信息失败");
        }

    }

}
