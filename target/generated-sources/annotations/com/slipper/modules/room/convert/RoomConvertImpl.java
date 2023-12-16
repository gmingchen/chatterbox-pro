package com.slipper.modules.room.convert;

import com.slipper.modules.room.entity.RoomEntity;
import com.slipper.modules.room.model.dto.RoomCreateDTO;
import com.slipper.modules.roomFriend.model.dto.RoomFriendCreateDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-16T10:45:35+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class RoomConvertImpl implements RoomConvert {

    @Override
    public RoomEntity convert(RoomCreateDTO bean) {
        if ( bean == null ) {
            return null;
        }

        RoomEntity roomEntity = new RoomEntity();

        roomEntity.setType( bean.getType() );

        return roomEntity;
    }

    @Override
    public RoomFriendCreateDTO convertFriend(RoomCreateDTO bean) {
        if ( bean == null ) {
            return null;
        }

        RoomFriendCreateDTO roomFriendCreateDTO = new RoomFriendCreateDTO();

        roomFriendCreateDTO.setSourceUserId( bean.getSourceUserId() );
        roomFriendCreateDTO.setTargetUserId( bean.getTargetUserId() );

        return roomFriendCreateDTO;
    }
}
