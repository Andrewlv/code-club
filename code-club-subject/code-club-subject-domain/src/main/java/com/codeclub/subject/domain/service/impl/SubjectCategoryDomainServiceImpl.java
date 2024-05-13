package com.codeclub.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.codeclub.subject.common.enums.IsDeletedFlagEnum;
import com.codeclub.subject.domain.convert.SubjectCategoryConverter;
import com.codeclub.subject.domain.entity.SubjectCategoryBO;
import com.codeclub.subject.domain.entity.SubjectLabelBO;
import com.codeclub.subject.domain.service.SubjectCategoryDomainService;
import com.codeclub.subject.infra.basic.entity.SubjectCategory;
import com.codeclub.subject.infra.basic.entity.SubjectLabel;
import com.codeclub.subject.infra.basic.entity.SubjectMapping;
import com.codeclub.subject.infra.basic.service.SubjectCategoryService;
import com.codeclub.subject.infra.basic.service.SubjectLabelService;
import com.codeclub.subject.infra.basic.service.SubjectMappingService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {


    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private ThreadPoolExecutor labelThreadPool;

    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.add.bo:{}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectCategoryService.insert(subjectCategory);
    }


    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> boList = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryList);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.queryPrimaryCategory.boList:{}",
                    JSON.toJSONString(boList));
        }
        boList.forEach(bo -> {
            Integer subjectCount = subjectCategoryService.querySubjectCount(bo.getId());
            bo.setCount(subjectCount);
        });
        return boList;
    }

    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    @Override
    @SneakyThrows
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO) {
        // 查询当前大类下所有分类
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(subjectCategoryBO.getId());
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.queryCategoryAndLabel.subjectCategoryList:{}",
                    JSON.toJSONString(subjectCategoryList));
        }
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConverter.INSTANCE.convertCategoryListToBOList(subjectCategoryList);
        // 线程池并发调用
        Map<Long, List<SubjectLabelBO>> map = new HashMap<>();
        List<CompletableFuture<Map<Long, List<SubjectLabelBO>>>> completableFutureList = subjectCategoryBOList.stream().map(category ->
                        CompletableFuture.supplyAsync(() -> getSubjectLabelBOList(category), labelThreadPool))
                .collect(Collectors.toList());
        completableFutureList.forEach(future->{
            try {
                Map<Long, List<SubjectLabelBO>> resultMap = future.get();
                map.putAll(resultMap);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        subjectCategoryBOList.forEach(categoryBO -> {
            categoryBO.setSubjectLabelBOList(map.get(categoryBO.getId()));
        });
        return subjectCategoryBOList;

        // 一次性获取标签信息
//        List<FutureTask<Map<Long, List<SubjectLabelBO>>>> futureTaskList = new LinkedList<>();
//
//        subjectCategoryBOList.forEach(category -> {
//            FutureTask<Map<Long, List<SubjectLabelBO>>> futureTask = new FutureTask<>(() -> getSubjectLabelBOList(category));
//            futureTaskList.add(futureTask);
//            labelThreadPool.submit(futureTask);
//        });
//        for (FutureTask<Map<Long, List<SubjectLabelBO>>> futureTask : futureTaskList) {
//            Map<Long, List<SubjectLabelBO>> resultMap = futureTask.get();
//            if (CollectionUtils.isEmpty(resultMap)) {
//                continue;
//            }
//            map.putAll(resultMap);
//        }
    }

    private Map<Long, List<SubjectLabelBO>> getSubjectLabelBOList(SubjectCategoryBO category) {
        Map<Long, List<SubjectLabelBO>> labelMap = new HashMap<>();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(category.getId());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(mappingList)) {
            return null;
        }
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryById(labelIdList);
        List<SubjectLabelBO> subjectLabelBOList = new LinkedList<>();
        subjectLabelList.forEach(label -> {
            SubjectLabelBO subjectLabelBO = new SubjectLabelBO();
            subjectLabelBO.setId(label.getId());
            subjectLabelBO.setLabelName(label.getLabelName());
            subjectLabelBO.setSortNum(label.getSortNum());
            subjectLabelBOList.add(subjectLabelBO);
        });
        labelMap.put(category.getId(), subjectLabelBOList);
        return labelMap;
    }
}
