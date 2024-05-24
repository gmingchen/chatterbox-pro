package com.slipper.common.enums;

import com.slipper.core.netty.core.WsMessageStrategy;
import com.slipper.core.netty.core.heartbeat.HeartbeatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * websocket 消息处理 枚举
 * @author gumingchen
 */
@Getter
@AllArgsConstructor
public enum WsMessageStrategyEnum implements EnumIntArray {
    /**
     * 心跳
     */
    HEARTBEAT(WsMessageTypeEnum.HEARTBEAT.getCode(), HeartbeatMessage.class),
    ;
    /**
     * 类型值
     */
    private final Integer code;
    /**
     * 实现类
     */
    private final Class<? extends WsMessageStrategy> wsMessageClass;
    /**
     * 枚举值数组
     */
    public static final int[] ARRAY = Arrays.stream(values()).mapToInt(WsMessageStrategyEnum::getCode).toArray();

    @Override
    public int[] array() {
        return ARRAY;
    }

    /**
     * 通过CODE获取枚举
     * @param code
     * @return
     */
    public static WsMessageStrategyEnum getByCode(int code) {
        for (WsMessageStrategyEnum wsMessageStrategyEnum : WsMessageStrategyEnum.values()) {
            if (WsMessageStrategyEnum.HEARTBEAT.getCode() == code) {
                return wsMessageStrategyEnum;
            }
        }
        return null;
    }
}
