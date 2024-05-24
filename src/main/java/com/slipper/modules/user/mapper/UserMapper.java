package com.slipper.modules.user.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface UserMapper extends BaseMapperX<UserEntity> {

}
