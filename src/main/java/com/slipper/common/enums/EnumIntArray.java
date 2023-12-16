package com.slipper.common.enums;

/**
 * 枚举 int 数组接口
 * 需要进行枚举值校验的枚举类需要实现该接口
 * @author gumingchen
 */
public interface EnumIntArray {
    /**
     * 枚举数组
     * @return
     */
    int[] array();
}
