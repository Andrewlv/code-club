package com.codeclub.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.codeclub.auth.common.enums.AuthUserStatusEnum;
import com.codeclub.auth.common.enums.IsDeletedFlagEnum;
import com.codeclub.auth.domain.constants.AuthConstant;
import com.codeclub.auth.domain.convert.AuthUserBOConverter;
import com.codeclub.auth.domain.entity.AuthUserBO;
import com.codeclub.auth.domain.service.AuthUserDomainService;
import com.codeclub.auth.infra.basic.entity.AuthRole;
import com.codeclub.auth.infra.basic.entity.AuthUser;
import com.codeclub.auth.infra.basic.entity.AuthUserRole;
import com.codeclub.auth.infra.basic.service.AuthRoleService;
import com.codeclub.auth.infra.basic.service.AuthUserRoleService;
import com.codeclub.auth.infra.basic.service.AuthUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthRoleService authRoleService;

    @Resource
    private AuthUserRoleService authUserRoleService;

    private String salt = "chicken";

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), salt));
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);

        // 建立角色关联
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole roleResult = authRoleService.queryByCondition(authRole);
        Long roleId = roleResult.getId();
        Long userId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(userId);
        authUserRole.setRoleId(roleId);
        authUserRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        authUserRoleService.insert(authUserRole);
        return count > 0;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        Integer count = authUserService.update(authUser);
        // 有任何的更新，都要与缓存进行同步修改
        return count > 0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBO.getId());
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authUserService.update(authUser);
        // 有任何的更新，都要与缓存进行同步修改
        return count > 0;
    }
}
