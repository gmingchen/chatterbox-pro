package com.slipper.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * websocket消息类型枚举类
 * @author gumingchen
 */
@Getter
@AllArgsConstructor
public enum WsMessageTypeEnum implements EnumIntArray {
    /**
     * websocket消息类型
     */
    HEARTBEAT(0, "心跳"),
    ACK(1, "ack确认"),
    PRIVATE_CHAT_MESSAGE(2, "私聊消息"),
    GROUP_CHAT_MESSAGE(3, "群聊消息"),
    ERROR(4, "异常消息"),
    FRIEND_APPLY(5, "好友申请"),
    AGREE_FRIEND_APPLY(6, "同意好友申请"),
    REFUSE_FRIEND_APPLY(7, "拒绝好友申请"),
    DELETE_FRIEND(8, "删除好友"),
    ;

    /**
     * 类型值
     */
    private final Integer code;
    /**
     * 类型名
     */
    private final String message;
    /**
     * 枚举值数组
     */
    public static final int[] ARRAY = Arrays.stream(values()).mapToInt(WsMessageTypeEnum::getCode).toArray();

    @Override
    public int[] array() {
        return ARRAY;
    }
}
