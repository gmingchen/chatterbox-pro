package com.slipper.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 房间类型枚举类
 * @author gumingchen
 */
@Getter
@AllArgsConstructor
public enum RoomTypeEnum implements EnumIntArray {
    /**
     * 房间类型
     */
    FRIEND(0, "好友"),
    GROUP(1, "群组"),
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
    public static final int[] ARRAY = Arrays.stream(values()).mapToInt(RoomTypeEnum::getCode).toArray();

    @Override
    public int[] array() {
        return ARRAY;
    }
}
