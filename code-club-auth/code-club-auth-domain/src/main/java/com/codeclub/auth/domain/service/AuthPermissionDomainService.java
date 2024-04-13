package com.codeclub.auth.domain.service;

import com.codeclub.auth.domain.entity.AuthPermissionBO;
import com.codeclub.auth.domain.entity.AuthRoleBO;

/**
 * 角色领域service
 */
public interface AuthPermissionDomainService {

    Boolean add(AuthPermissionBO authPermissionBO);

    Boolean update(AuthPermissionBO authPermissionBO);

    Boolean delete(AuthPermissionBO authPermissionBO);
}
