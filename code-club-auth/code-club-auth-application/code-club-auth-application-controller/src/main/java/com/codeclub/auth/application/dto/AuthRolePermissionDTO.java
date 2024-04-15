package com.codeclub.auth.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色权限关联dto
 */
@Data
public class AuthRolePermissionDTO implements Serializable {
    private static final long serialVersionUID = 827443933324431831L;

    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限id
     */
    private Long permissionId;

    private List<Long> permissionIdList;

}

