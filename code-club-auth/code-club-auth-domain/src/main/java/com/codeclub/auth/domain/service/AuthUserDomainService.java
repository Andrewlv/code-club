package com.codeclub.auth.domain.service;

import com.codeclub.auth.domain.entity.AuthUserBO;

/**
 * 用户领域service
 */
public interface AuthUserDomainService {

    /**
     * 用户注册
     *
     * @param authUserBO
     * @return
     */
    Boolean register(AuthUserBO authUserBO);

    /**
     * 更新用户信息
     *
     * @param authUserBO
     * @return
     */
    Boolean update(AuthUserBO authUserBO);

    /**
     * 删除用户
     *
     * @param authUserBO
     * @return
     */
    Boolean delete(AuthUserBO authUserBO);
}
