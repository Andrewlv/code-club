package com.codeclub.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.codeclub.subject.domain.convert.SubjectInfoConverter;
import com.codeclub.subject.domain.entity.SubjectInfoBO;
import com.codeclub.subject.domain.handler.subject.SubjectTypeHandler;
import com.codeclub.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.codeclub.subject.domain.service.SubjectInfoDomainService;
import com.codeclub.subject.infra.basic.entity.SubjectInfo;
import com.codeclub.subject.infra.basic.entity.SubjectMapping;
import com.codeclub.subject.infra.basic.service.SubjectInfoService;
import com.codeclub.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;


    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectInfoBO));
        }
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBOToInfo(subjectInfoBO);
        subjectInfoService.insert(subjectInfo);
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        handler.add(subjectInfoBO);
        List<Integer> categoryIds = subjectInfoBO.getCategoryIds();
        List<Integer> labelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> mappingList = new LinkedList<>();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId -> {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setCategoryId(Long.valueOf(categoryId));
                subjectMapping.setLabelId(Long.valueOf(labelId));
                subjectMapping.setSubjectId(subjectInfo.getId());
                mappingList.add(subjectMapping);
            });
        });
        subjectMappingService.batchInsert(mappingList);
    }
}
