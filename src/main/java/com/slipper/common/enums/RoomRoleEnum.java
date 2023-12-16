package com.slipper.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 房间角色枚举类
 * @author gumingchen
 */
@Getter
@AllArgsConstructor
public enum RoomRoleEnum implements EnumIntArray {
    /**
     * 房间角色
     */
    LEADER(0, "群主"),
    ADMIN(1, "管理员"),
    MEMBER(1, "成员"),
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
    public static final int[] ARRAY = Arrays.stream(values()).mapToInt(RoomRoleEnum::getCode).toArray();

    @Override
    public int[] array() {
        return ARRAY;
    }
}
