package com.codeclub.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色权限关联bo
 */
@Data
public class AuthRolePermissionBO implements Serializable {
    private static final long serialVersionUID = 827443933324431831L;

    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限id
     */
    private List<Long> permissionIdList;

}

