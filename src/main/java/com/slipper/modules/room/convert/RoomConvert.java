package com.slipper.modules.room.convert;

import com.slipper.modules.room.entity.RoomEntity;
import com.slipper.modules.room.model.dto.RoomCreateDTO;
import com.slipper.modules.roomFriend.model.dto.RoomFriendCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 房间
 * @author gumingchen
 */
@Mapper
public interface RoomConvert {
    RoomConvert INSTANCE = Mappers.getMapper(RoomConvert.class);


    RoomEntity convert(RoomCreateDTO bean);

    RoomFriendCreateDTO convertFriend(RoomCreateDTO bean);
}
