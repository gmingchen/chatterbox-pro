package com.slipper.modules.token.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.slipper.common.constant.RedisConstant;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.core.jwt.utils.JwtUtils;
import com.slipper.core.redis.utils.RedisUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.token.model.dto.TokenDTO;
import com.slipper.modules.token.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author gumingchen
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public TokenDTO generate(Long userId) {
        String token = jwtUtils.generate(userId);

        TokenDTO tokenDTO = new TokenDTO()
                .setUserId(userId)
                .setToken(token)
                .setExpiredAt(jwtUtils.getExpire(token));

        String key = RedisConstant.TOKEN + userId;
        redisUtils.set(key, tokenDTO, jwtUtils.getExpireDuration(token));

        return tokenDTO;
    }

    @Override
    public void destruction(Long userId) {
        String key = RedisConstant.TOKEN + userId;
        redisUtils.delete(key);
    }

    @Override
    public Boolean validate(String token) {
        TokenDTO tokenDTO = this.getTokenInfo(token);
        return jwtUtils.validate(token) && ObjectUtil.isNotNull(tokenDTO);
    }

    @Override
    public void validateExpired(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new RunException(ResultCodeEnum.NOT_LOGIN);
        } else if (Boolean.FALSE.equals(validate(token))) {
            throw new RunException(ResultCodeEnum.TOKEN_EXPIRE);
        }
    }

    @Override
    public TokenDTO getTokenInfo(String token) {
        Long userId = jwtUtils.getClaimsValue(token, Long.class);
        String key = RedisConstant.TOKEN + userId;
        return redisUtils.get(key, TokenDTO.class).orElse(null);
    }
}
