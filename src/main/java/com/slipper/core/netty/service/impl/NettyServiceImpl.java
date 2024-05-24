package com.slipper.core.netty.service.impl;

import cn.hutool.json.JSONUtil;
import com.slipper.common.enums.UserOnlineStatusEnum;
import com.slipper.common.enums.WsMessageStrategyEnum;
import com.slipper.core.netty.core.WsMessageContext;
import com.slipper.core.netty.dto.WsRequestDTO;
import com.slipper.core.netty.service.NettyService;
import com.slipper.modules.auth.service.AuthService;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import com.slipper.modules.user.service.UserService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * netty
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("nettyService")
public class NettyServiceImpl implements NettyService {

    @Resource
    private AuthService authService;
    @Resource
    private UserService userService;
    @Resource
    private WsMessageContext wsMessageContext;
//
    @Override
    public boolean validateToken(String token) {
        return authService.validateToken(token);
    }

    @Override
    public LoginUserDTO queryUser(String token) {
        return authService.queryUser(token);
    }

    @Override
    public void updateOnline(Long userId, UserOnlineStatusEnum online) {
        userService.updateOnline(userId, online);
    }

    @Override
    public void messageHandle(Channel channel, String message) {
        // 获取消息内容
        WsRequestDTO wsRequestDTO = JSONUtil.toBean(message, WsRequestDTO.class);
        Optional.ofNullable(WsMessageStrategyEnum.getByCode(wsRequestDTO.getType()))
                .ifPresent(wsMessageStrategyEnum -> wsMessageContext.run(wsMessageStrategyEnum, channel, wsRequestDTO));
    }
}