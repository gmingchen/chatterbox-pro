package com.slipper.core.mybatisplus.utils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.slipper.common.pojo.OrderField;
import com.slipper.common.pojo.PageParam;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * mybatis 工具类
 * @author gumingchen
 */
public class MyBatisUtils {

    public static <T> Page<T> buildPage (PageParam pageParam) {
        return buildPage(pageParam, null);
    }

    public static <T> Page<T> buildPage (PageParam pageParam, Collection<OrderField> orderFields) {
        Page<T> page = new Page<T>(pageParam.getCurrent(), pageParam.getSize());
        if (!CollectionUtils.isEmpty(orderFields)) {
            page.addOrder(
                    orderFields.stream()
                    .map(orderField -> OrderField.ASC.equals(orderField.getOrder())
                            ? OrderItem.asc(orderField.getField())
                            : OrderItem.desc(orderField.getField()))
                    .collect(Collectors.toList())
            );
        }
        return page;
    }
}
