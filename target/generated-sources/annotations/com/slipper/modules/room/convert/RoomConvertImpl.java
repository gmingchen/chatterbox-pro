package com.slipper.modules.room.convert;

import com.slipper.modules.room.model.req.RoomGroupCreateReqVO;
import com.slipper.modules.roomGroup.model.dto.RoomGroupCreateDTO;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-22T16:29:49+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
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
