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
public enum ApplyStatusEnum implements EnumIntArray {
    /**
     * 申请
     */
    AUDIT(0, "待审核"),
    PASS(1, "已通过"),
    REJECT(2, "已拒绝"),
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
    public static final int[] ARRAY = Arrays.stream(values()).mapToInt(ApplyStatusEnum::getCode).toArray();

    @Override
    public int[] array() {
        return ARRAY;
    }
}
