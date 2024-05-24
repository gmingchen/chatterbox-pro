package com.slipper.modules.roomGroup.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.roomGroup.entity.RoomGroupEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 群房间
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface RoomGroupMapper extends BaseMapperX<RoomGroupEntity> {

}
