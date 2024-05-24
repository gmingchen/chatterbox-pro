package com.slipper.modules.roomFriend.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.roomFriend.entity.RoomFriendEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 好友房间
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface RoomFriendMapper extends BaseMapperX<RoomFriendEntity> {


}
