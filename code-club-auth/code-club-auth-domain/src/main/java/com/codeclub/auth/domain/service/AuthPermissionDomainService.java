package com.codeclub.auth.domain.service;

import com.codeclub.auth.domain.entity.AuthPermissionBO;
import com.codeclub.auth.domain.entity.AuthRoleBO;

import java.util.List;

/**
 * 角色领域service
 */
public interface AuthPermissionDomainService {

    Boolean add(AuthPermissionBO authPermissionBO);

    Boolean update(AuthPermissionBO authPermissionBO);

    Boolean delete(AuthPermissionBO authPermissionBO);

    List<String> getPermission(String userName);
}
