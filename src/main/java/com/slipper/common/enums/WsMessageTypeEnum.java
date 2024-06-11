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
    PRIVATE_CHAT_MESSAGE(1, "私聊消息"),
    GROUP_CHAT_MESSAGE(2, "群聊消息"),

    FRIEND_APPLY(3, "好友申请"),
    PASS_FRIEND_APPLY(4, "同意好友申请"),
    REJECT_FRIEND_APPLY(5, "拒绝好友申请"),
    DELETE_FRIEND(6, "删除好友"),

    GROUP_APPLY(7, "群聊申请"),
    AGREE_GROUP_APPLY(8, "同意群聊申请"),
    REFUSE_GROUP_APPLY(9, "拒绝群聊申请"),
    DELETE_GROUP_APPLY(10, "删除群聊"),
    EXIT_GROUP_APPLY(11, "退出群聊"),

    ERROR(20, "异常消息"),
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
