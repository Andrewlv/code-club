package com.codeclub.auth.application.convert;

import com.codeclub.auth.application.dto.AuthUserDTO;
import com.codeclub.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * 用户dto转换器
 */
@Mapper
public interface AuthUserDTOConverter {

    AuthUserDTOConverter INSTANCE = Mappers.getMapper(AuthUserDTOConverter.class);

    AuthUserBO convertDTOToBO(AuthUserDTO authUserDTO);
    AuthUserDTO convertBOToDTO(AuthUserBO authUserBO);
}
