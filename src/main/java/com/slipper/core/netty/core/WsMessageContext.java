package com.slipper.core.netty.core;

import cn.hutool.core.util.ObjectUtil;
import com.slipper.common.enums.WsMessageStrategyEnum;
import com.slipper.common.utils.ApplicationContextUtils;
import com.slipper.core.netty.dto.WsRequestDTO;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 上下文
 * @author gumingchen
 */
@Component
public class WsMessageContext {

    public void run(WsMessageStrategyEnum wsMessageStrategyEnum, Channel channel, WsRequestDTO wsRequestDTO) {
        Class<? extends WsMessageStrategy> wsMessageStrategy = wsMessageStrategyEnum.getWsMessageClass();
        if (ObjectUtil.isNotEmpty(wsMessageStrategy)) {
            Optional.ofNullable(ApplicationContextUtils.getBean(wsMessageStrategy))
                    .ifPresent(wsMessageStrategyImpl -> wsMessageStrategyImpl.run(channel, wsRequestDTO));
        }
    }
}
