package com.codeclub.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.codeclub.subject.application.convert.SubjectAnswerDTOConverter;
import com.codeclub.subject.application.convert.SubjectInfoDTOConverter;
import com.codeclub.subject.application.dto.SubjectInfoDTO;
import com.codeclub.subject.common.entity.Result;
import com.codeclub.subject.domain.entity.SubjectAnswerBO;
import com.codeclub.subject.domain.entity.SubjectInfoBO;
import com.codeclub.subject.infra.basic.service.SubjectCategoryService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 刷题Controller
 */
@RestController
@Slf4j
public class SubjectController {

    @Resource
    private SubjectCategoryService subjectCategoryService;

    /**
     * 新增题目
     *
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.add.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(subjectInfoDTO.getSubjectName()), " 题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(), "题目难度不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(), "题目类型不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(), "题目分数不能为空");
            Preconditions.checkArgument(CollectionUtils.isEmpty(subjectInfoDTO.getCategoryIds()), "分类id不能为空");
            Preconditions.checkArgument(CollectionUtils.isEmpty(subjectInfoDTO.getLabelIds()), "标签id不能为空");
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE
                    .convertDTOToBO(subjectInfoDTO);
            List<SubjectAnswerBO> subjectAnswerBOS = SubjectAnswerDTOConverter.INSTANCE.convertListDTOToBO(subjectInfoDTO.getOptionList());
            subjectCategoryDomainService.add(subjectAnswerBOS);
            return Result.ok(true);
        } catch (Exception e) {
            return Result.fail();
        }
    }
}
