package com.slipper.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 消息状态枚举类
 * @author gumingchen
 */
@Getter
@AllArgsConstructor
public enum MessageStatusEnum implements EnumIntArray {
    /**
     * 消息状态
     */
    WITHDRAW(0, "撤回"),
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
    public static final int[] ARRAY = Arrays.stream(values()).mapToInt(MessageStatusEnum::getCode).toArray();

    @Override
    public int[] array() {
        return ARRAY;
    }
}
