package com.slipper.core.netty.core;

import cn.hutool.json.JSONUtil;
import com.slipper.common.utils.CollectionUtils;
import com.slipper.core.netty.dto.WsResponseDTO;
import com.slipper.core.netty.utils.WebSocketUsers;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.channel.Channel;

import java.util.List;

/**
 * @author gumingchen
 */
public abstract class WsMessageStrategyAbstract implements WsMessageStrategy {

    /**
     * 发送消息
     * @param wsResponseDTO 消息对象
     * @param channel 管道
     */
    protected void send(WsResponseDTO<?> wsResponseDTO, Channel channel) {
        String response = JSONUtil.toJsonStr(wsResponseDTO);
        channel.write(new TextWebSocketFrame(response));
    }

    /**
     * 发送消息
     * @param wsResponseDTO 消息对象
     * @param userIds 用户ID数组
     */
    protected void send(WsResponseDTO<?> wsResponseDTO, List<Long> userIds) {
        WebSocketUsers.sendMessage(wsResponseDTO, CollectionUtils.mapList(userIds, Object::toString));
    }

    /**
     * 发送消息
     * @param wsResponseDTO 消息对象
     * @param userId 用户ID
     */
    protected void send(WsResponseDTO<?> wsResponseDTO, Long userId) {
        WebSocketUsers.sendMessage(wsResponseDTO, userId.toString());
    }
}
