package com.codeclub.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息bo
 */
@Data
public class AuthUserBO implements Serializable {
    private static final long serialVersionUID = 308104060945991502L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户名称/账号
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 状态 0启用 1禁用
     */
    private Integer status;
    /**
     * 个人介绍
     */
    private String introduce;
    /**
     * 特殊字段
     */
    private String extJson;
}

