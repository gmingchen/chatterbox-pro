package com.slipper.modules.apply.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.apply.entity.ApplyEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 申请
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface ApplyMapper extends BaseMapperX<ApplyEntity> {

}
