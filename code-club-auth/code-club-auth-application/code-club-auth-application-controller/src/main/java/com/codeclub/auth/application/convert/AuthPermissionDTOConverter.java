package com.codeclub.auth.application.convert;

import com.codeclub.auth.application.dto.AuthPermissionDTO;
import com.codeclub.auth.application.dto.AuthRoleDTO;
import com.codeclub.auth.domain.entity.AuthPermissionBO;
import com.codeclub.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * 用户dto转换器
 */
@Mapper
public interface AuthPermissionDTOConverter {

    AuthPermissionDTOConverter INSTANCE = Mappers.getMapper(AuthPermissionDTOConverter.class);

    AuthPermissionBO convertDTOToBO(AuthPermissionDTO authPermissionDTO);
}
