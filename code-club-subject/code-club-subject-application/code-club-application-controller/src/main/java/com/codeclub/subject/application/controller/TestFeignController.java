package com.codeclub.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.codeclub.subject.common.entity.PageResult;
import com.codeclub.subject.infra.basic.entity.SubjectInfoEs;
import com.codeclub.subject.infra.basic.service.SubjectEsService;
import com.codeclub.subject.infra.entity.UserInfo;
import com.codeclub.subject.infra.rpc.UserRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
