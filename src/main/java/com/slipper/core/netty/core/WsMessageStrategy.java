package com.slipper.core.netty.core;

import com.slipper.core.netty.dto.WsRequestDTO;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import io.netty.channel.Channel;

/**
 * @author gumingchen
 */
public interface WsMessageStrategy {
    /**
     * 执行
     * @param channel 管道
     * @param wsRequestDTO 消息对象
     */
    void run(Channel channel, WsRequestDTO wsRequestDTO);
}
