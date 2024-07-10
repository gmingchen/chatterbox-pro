package com.slipper.core.netty.service.impl;

import cn.hutool.json.JSONUtil;
import com.slipper.common.enums.UserOnlineStatusEnum;
import com.slipper.common.enums.WsMessageStrategyEnum;
import com.slipper.common.enums.WsMessageTypeEnum;
import com.slipper.common.utils.CollectionUtils;
import com.slipper.core.netty.core.WsMessageContext;
import com.slipper.core.netty.dto.WsRequestDTO;
import com.slipper.core.netty.dto.WsResponseDTO;
import com.slipper.core.netty.service.NettyService;
import com.slipper.core.netty.utils.WebSocketUsers;
import com.slipper.modules.auth.service.AuthService;
import com.slipper.modules.friend.service.FriendService;
import com.slipper.modules.roomGroupUser.service.RoomGroupUserService;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import com.slipper.modules.user.service.UserService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Resource
    private FriendService friendService;
    @Resource
    private RoomGroupUserService roomGroupUserService;

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
        // 获取所有好友用户ID
        List<Long> friendIds = friendService.queryFriendIds(userId);
        // 获取所有群友用户ID
        List<Long> groupUserIds = roomGroupUserService.queryGroupUserIds(userId);
        // 去重合并之后的所有用户ID
        List<Long> ids = Stream.concat(friendIds.stream(), groupUserIds.stream())
                .distinct()
                .collect(Collectors.toList());
        if (!ids.isEmpty()) {
            // todo: Websocket 通知用户...上下线
            Integer type = online.getCode().equals(UserOnlineStatusEnum.ONLINE.getCode())
                    ? WsMessageTypeEnum.USER_ONLINE.getCode()
                    : WsMessageTypeEnum.USER_OFFLINE.getCode();
            WebSocketUsers.sendMessage(
                    new WsResponseDTO<>()
                            .setType(type)
                            .setBody(userId),
                    CollectionUtils.mapList(ids, Object::toString));
        }
    }

    @Override
    public void messageHandle(Channel channel, String message) {
        // 获取消息内容
        WsRequestDTO wsRequestDTO = JSONUtil.toBean(message, WsRequestDTO.class);
        Optional.ofNullable(WsMessageStrategyEnum.getByCode(wsRequestDTO.getType()))
                .ifPresent(wsMessageStrategyEnum -> wsMessageContext.run(wsMessageStrategyEnum, channel, wsRequestDTO));
    }
}