package com.slipper.modules.roomFriend.convert;

import com.slipper.modules.roomFriend.entity.RoomFriendEntity;
import com.slipper.modules.roomFriend.model.dto.RoomFriendCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 好友房间
 * @author gumingchen
 */
@Mapper
public interface RoomFriendConvert {
    RoomFriendConvert INSTANCE = Mappers.getMapper(RoomFriendConvert.class);

    RoomFriendEntity convert(RoomFriendCreateDTO bean);
}
