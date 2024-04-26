package com.codeclub.auth.domain.convert;

import com.codeclub.auth.domain.entity.AuthUserBO;
import com.codeclub.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * 用户bo转换器
 */
@Mapper
public interface AuthUserBOConverter {

    AuthUserBOConverter INSTANCE = Mappers.getMapper(AuthUserBOConverter.class);

    AuthUser convertBOToEntity(AuthUserBO authUserBO);
    AuthUserBO convertEntityToBO(AuthUser authUser);
}
