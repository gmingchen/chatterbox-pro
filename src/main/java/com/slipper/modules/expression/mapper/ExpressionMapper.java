package com.slipper.modules.expression.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.expression.entity.ExpressionEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 表情
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface ExpressionMapper extends BaseMapperX<ExpressionEntity> {

}
