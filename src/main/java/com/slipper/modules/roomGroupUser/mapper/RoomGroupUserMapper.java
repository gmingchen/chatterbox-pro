package com.slipper.modules.roomGroupUser.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.roomGroupUser.entity.RoomGroupUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 群房间_用户
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface RoomGroupUserMapper extends BaseMapperX<RoomGroupUserEntity> {

}
