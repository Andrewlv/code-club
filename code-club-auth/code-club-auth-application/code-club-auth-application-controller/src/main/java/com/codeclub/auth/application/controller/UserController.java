package com.codeclub.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSON;
import com.codeclub.auth.application.convert.AuthUserDTOConverter;
import com.codeclub.auth.application.dto.AuthUserDTO;
import com.codeclub.auth.common.entity.Result;
import com.codeclub.auth.domain.entity.AuthUserBO;
import com.codeclub.auth.domain.service.AuthUserDomainService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Resource
    private AuthUserDomainService authUserDomainService;

    /**
     * 用户注册
     *
     * @param authUserDTO
     * @return
     */
    @RequestMapping("register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.register.dto:{}", JSON.toJSONString(authUserDTO));
                Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
                Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getEmail()), "邮箱地址不能为空");
                Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()), "密码不能为空");
                AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
                return Result.ok(authUserDomainService.register(authUserBO));
            }
        } catch (Exception e) {
            log.error("UserController.register.error:{}", e.getMessage(), e);
            return Result.fail("注册用户失败");
        }
        return null;
    }

    @RequestMapping("doLogin")
    public SaResult doLogin(String username, String password) {
        if ("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return SaResult.data(tokenInfo);
        }
        return SaResult.error("登录失败");
    }

    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

}
