package com.slipper.core.netty.service;

import com.slipper.common.enums.UserOnlineStatusEnum;
import com.slipper.core.netty.dto.WsRequestDTO;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import io.netty.channel.Channel;

/**
 * netty
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface NettyService {

    /**
     * 校验token是否过期
     * @param token 凭证
     * @return
     */
    boolean validateToken(String token);
    /**
     * 通过token查询用户信息
     * @param token 凭证
     * @return
     */
    LoginUserDTO queryUser(String token);

    /**
     * 更新用户在线状态
     * @param userId 用户ID
     * @param online 在线状态
     */
    void updateOnline(Long userId, UserOnlineStatusEnum online);

    /**
     * 消息处理
     * @param channel 管道
     * @param message 消息内容
     */
    void messageHandle(Channel channel, String message);

}

