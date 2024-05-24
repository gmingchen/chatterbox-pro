package com.slipper.modules.room.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.room.entity.RoomEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 房间
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface RoomMapper extends BaseMapperX<RoomEntity> {

}
