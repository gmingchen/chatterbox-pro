package com.slipper.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 用户在线状态枚举
 * @author gumingchen
 */
@Getter
@AllArgsConstructor
public enum UserOnlineStatusEnum implements EnumIntArray {
    /**
     * 用户在线状态
     */
    OFFLINE(0, "离线"),
    ONLINE(1, "在线"),
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
    public static final int[] ARRAY = Arrays.stream(values()).mapToInt(UserOnlineStatusEnum::getCode).toArray();

    @Override
    public int[] array() {
        return ARRAY;
    }
}
