package com.codeclub.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.codeclub.subject.application.convert.SubjectAnswerDTOConverter;
import com.codeclub.subject.application.convert.SubjectInfoDTOConverter;
import com.codeclub.subject.application.dto.SubjectInfoDTO;
import com.codeclub.subject.common.entity.PageResult;
import com.codeclub.subject.common.entity.Result;
import com.codeclub.subject.domain.entity.SubjectAnswerBO;
import com.codeclub.subject.domain.entity.SubjectInfoBO;
import com.codeclub.subject.domain.service.SubjectInfoDomainService;
import com.codeclub.subject.infra.entity.UserInfo;
import com.codeclub.subject.infra.rpc.UserRpc;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 刷题Controller
 */
@RestController
@Slf4j
@RequestMapping("/subject/category")
public class TestFeignController {


    @Resource
    private UserRpc userRpc;

    @GetMapping("testFeign")
    public void testFeign() {
        UserInfo userInfo = userRpc.getUserInfo("jichi");
        log.info("testFeign.userInfo:{}", userInfo);
    }
}
