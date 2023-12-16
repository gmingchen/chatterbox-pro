package com.slipper.common.constant;

import com.slipper.modules.user.model.dto.LoginUserDTO;
import io.netty.util.AttributeKey;

/**
 * WebSocket 常量
 * @author gumingchen
 */
public class WebSocketConstant {
    /**
     * 管道附属key
     */
    public static final AttributeKey<LoginUserDTO> ATTRIBUTE_KEY = AttributeKey.valueOf("user");
}
