package com.slipper.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 聊天类型枚举类
 * @author gumingchen
 */
@Getter
@AllArgsConstructor
public enum ChatTypeEnum implements EnumIntArray {
    /**
     * 聊天类型
     */
    PRIVATE(0, "私聊"),
    PUBLIC(1, "群聊"),
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
    public static final int[] ARRAY = Arrays.stream(values()).mapToInt(ChatTypeEnum::getCode).toArray();

    @Override
    public int[] array() {
        return ARRAY;
    }
}
