package com.slipper.modules.roomFriend.convert;

import com.slipper.modules.roomFriend.entity.RoomFriendEntity;
import com.slipper.modules.roomFriend.model.dto.RoomFriendCreateDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T23:09:44+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_411 (Oracle Corporation)"
)
public class RoomFriendConvertImpl implements RoomFriendConvert {

    @Override
    public RoomFriendEntity convert(RoomFriendCreateDTO bean) {
        if ( bean == null ) {
            return null;
        }

        RoomFriendEntity roomFriendEntity = new RoomFriendEntity();

        roomFriendEntity.setRoomId( bean.getRoomId() );
        roomFriendEntity.setSourceUserId( bean.getSourceUserId() );
        roomFriendEntity.setTargetUserId( bean.getTargetUserId() );

        return roomFriendEntity;
    }
}
