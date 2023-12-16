package com.slipper.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 申请枚举类
 * @author gumingchen
 */
@Getter
@AllArgsConstructor
public enum ApplyTypeEnum implements EnumIntArray {
    /**
     * 申请
     */
    FRIEND(0, "加好友"),
    GROUP(1, "加群"),
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
    public static final int[] ARRAY = Arrays.stream(values()).mapToInt(ApplyTypeEnum::getCode).toArray();

    @Override
    public int[] array() {
        return ARRAY;
    }
}
