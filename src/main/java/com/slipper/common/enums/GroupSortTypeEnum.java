package com.slipper.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 分组排序类型枚举类
 * @author gumingchen
 */
@Getter
@AllArgsConstructor
public enum GroupSortTypeEnum implements EnumIntArray {
    /**
     * 分组排序类型
     */
    BEFORE(0, "之前"),
    AFTER(1, "之后"),
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
    public static final int[] ARRAY = Arrays.stream(values()).mapToInt(GroupSortTypeEnum::getCode).toArray();

    @Override
    public int[] array() {
        return ARRAY;
    }
}
