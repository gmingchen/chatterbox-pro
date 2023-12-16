package com.slipper.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 消息类型枚举类
 * @author gumingchen
 */
@Getter
@AllArgsConstructor
public enum MessageTypeEnum implements EnumIntArray {
    /**
     * 消息类型
     */
    TEXT(0, "文本"),
    IMAGE(1, "图片"),
    VOICE(2, "语音"),
    FILE(3, "文件"),
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
    public static final int[] ARRAY = Arrays.stream(values()).mapToInt(MessageTypeEnum::getCode).toArray();

    @Override
    public int[] array() {
        return ARRAY;
    }
}
