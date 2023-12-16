package com.slipper.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 群房间用户状态枚举类
 * @author gumingchen
 */
@Getter
@AllArgsConstructor
public enum RoomGroupUserStatusEnum implements EnumIntArray {
    /**
     * 群房间用户状态
     */
    PROHIBITION(0, "禁用"),
    NORMAL(1, "正常"),
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
    public static final int[] ARRAY = Arrays.stream(values()).mapToInt(RoomGroupUserStatusEnum::getCode).toArray();

    @Override
    public int[] array() {
        return ARRAY;
    }
}
