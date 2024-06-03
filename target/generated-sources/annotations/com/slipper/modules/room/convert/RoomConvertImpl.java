package com.slipper.modules.room.convert;

import com.slipper.modules.room.model.req.RoomGroupCreateReqVO;
import com.slipper.modules.roomGroup.model.dto.RoomGroupCreateDTO;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2024-06-03T16:58:51+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
=======
    date = "2024-05-30T23:09:44+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_411 (Oracle Corporation)"
>>>>>>> 2c40374883c1479447478e870711c7435af9145c
)
public class RoomConvertImpl implements RoomConvert {

    @Override
    public RoomGroupCreateDTO convert(RoomGroupCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        RoomGroupCreateDTO roomGroupCreateDTO = new RoomGroupCreateDTO();

        roomGroupCreateDTO.setName( bean.getName() );
        roomGroupCreateDTO.setAvatar( bean.getAvatar() );
        Set<Long> set = bean.getUserIds();
        if ( set != null ) {
            roomGroupCreateDTO.setUserIds( new LinkedHashSet<Long>( set ) );
        }

        return roomGroupCreateDTO;
    }
}
