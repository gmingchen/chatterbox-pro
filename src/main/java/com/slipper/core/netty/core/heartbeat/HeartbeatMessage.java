package com.slipper.core.netty.core.heartbeat;

import com.slipper.common.enums.WsMessageTypeEnum;
import com.slipper.core.netty.core.WsMessageStrategyAbstract;
import com.slipper.core.netty.dto.WsRequestDTO;
import com.slipper.core.netty.dto.WsResponseDTO;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * 心跳消息
 * @author gumingchen
 */
@Component
public class HeartbeatMessage extends WsMessageStrategyAbstract {

    @Override
    public void run(Channel channel, WsRequestDTO wsRequestDTO) {
        send(new WsResponseDTO<>().setType(WsMessageTypeEnum.HEARTBEAT.getCode()), channel);
    }
}
