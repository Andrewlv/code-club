package com.codeclub.auth.domain.convert;

import com.codeclub.auth.domain.entity.AuthPermissionBO;
import com.codeclub.auth.infra.basic.entity.AuthPermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * 权限bo转换器
 */
@Mapper
public interface AuthPermissionBOConverter {

    AuthPermissionBOConverter INSTANCE = Mappers.getMapper(AuthPermissionBOConverter.class);

    AuthPermission convertBOToEntity(AuthPermissionBO authPermissionBO);
}
