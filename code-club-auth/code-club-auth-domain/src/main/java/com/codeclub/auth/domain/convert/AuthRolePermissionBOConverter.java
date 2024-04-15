package com.codeclub.auth.domain.convert;

import com.codeclub.auth.domain.entity.AuthPermissionBO;
import com.codeclub.auth.domain.entity.AuthRolePermissionBO;
import com.codeclub.auth.infra.basic.entity.AuthPermission;
import com.codeclub.auth.infra.basic.entity.AuthRolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * 权限bo转换器
 */
@Mapper
public interface AuthRolePermissionBOConverter {

    AuthRolePermissionBOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionBOConverter.class);

    AuthRolePermission convertBOToEntity(AuthRolePermissionBO authRolePermissionBO);
}
