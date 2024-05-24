package com.slipper.modules.token.service;

import com.slipper.modules.token.model.dto.TokenDTO;

/**
 * 登录凭证
 * @author gumingchen
 */
public interface TokenService {

    /**
     * 生成凭证
     * @param userId 用户ID
     * @return AuthTokenResVO
     */
    TokenDTO generate(Long userId);
    /**
     * 销毁凭证
     * @param userId 用户ID
     */
    void destruction(Long userId);

    /**
     * 校验凭证正确性
     * @param token 凭证
     * @return Boolean
     */
    Boolean validate(String token);
    /**
     * 校验是否过期
     * @param token 凭证
     */
    void validateExpired(String token);

    /**
     * 通过凭证获取用户ID
     * @param token 凭证
     * @return
     */
    TokenDTO getTokenInfo(String token);
}
