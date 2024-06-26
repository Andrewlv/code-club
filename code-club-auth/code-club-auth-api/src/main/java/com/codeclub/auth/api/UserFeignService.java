package com.codeclub.auth.api;

import com.codeclub.auth.entity.AuthUserDTO;
import com.codeclub.auth.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户服务feign
 */
@FeignClient("code-club-auth")
public interface UserFeignService {

    @RequestMapping("user/getUserInfo")
    Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO);
}
