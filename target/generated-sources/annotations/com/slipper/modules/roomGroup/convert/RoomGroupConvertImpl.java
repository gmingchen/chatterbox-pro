package com.slipper.modules.roomGroup.convert;

import com.slipper.modules.roomGroup.entity.RoomGroupEntity;
import com.slipper.modules.roomGroup.model.dto.RoomGroupCreateDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T23:09:44+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_411 (Oracle Corporation)"
)
public class RoomGroupConvertImpl implements RoomGroupConvert {

    @Override
    public RoomGroupEntity convert(RoomGroupCreateDTO bean) {
        if ( bean == null ) {
            return null;
        }

        RoomGroupEntity roomGroupEntity = new RoomGroupEntity();

        roomGroupEntity.setRoomId( bean.getRoomId() );
        roomGroupEntity.setName( bean.getName() );
        roomGroupEntity.setAvatar( bean.getAvatar() );

        return roomGroupEntity;
    }
}
