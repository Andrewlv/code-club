package com.codeclub.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.codeclub.auth.application.convert.AuthRolePermissionDTOConverter;
import com.codeclub.auth.application.dto.AuthRolePermissionDTO;
import com.codeclub.auth.entity.Result;
import com.codeclub.auth.domain.entity.AuthRolePermissionBO;
import com.codeclub.auth.domain.service.AuthRolePermissionDomainService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色权限controller
 */
@RestController
@RequestMapping("rolePermission")
@Slf4j
public class RolePermissionController {

    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;

    /**
     * 新增角色权限关联关系
     *
     * @param authRolePermissionDTO
     * @return
     */
    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RolePermissionController.add.dto:{}", JSON.toJSONString(authRolePermissionDTO));
            }
            Preconditions.checkArgument(!CollectionUtils.isEmpty(authRolePermissionDTO.getPermissionIdList()), "权限列表不能为空");
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(), "角色不能为空");
            AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE.convertDTOToBO(authRolePermissionDTO);
            return Result.ok(authRolePermissionDomainService.add(authRolePermissionBO));
        } catch (Exception e) {
            log.error("RolePermissionController.add.error:{}", e.getMessage(), e);
            return Result.fail("新增角色权限失败");
        }
    }
}
