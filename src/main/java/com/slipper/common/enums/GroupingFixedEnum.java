package com.slipper.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 分组是否固定
 * @author gumingchen
 */
@Getter
@AllArgsConstructor
public enum GroupingFixedEnum implements EnumIntArray {
    /**
     * 分组是否固定
     */
    NO(0, "不固定"),
    YES(1, "固定"),
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
    public static final int[] ARRAY = Arrays.stream(values()).mapToInt(GroupingFixedEnum::getCode).toArray();

    @Override
    public int[] array() {
        return ARRAY;
    }
}
