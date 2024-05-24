package com.slipper.modules.room.convert;

import com.slipper.modules.room.model.req.RoomGroupCreateReqVO;
import com.slipper.modules.roomGroup.model.dto.RoomGroupCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 房间
 * @author gumingchen
 */
@Mapper
public interface RoomConvert {
    RoomConvert INSTANCE = Mappers.getMapper(RoomConvert.class);

    RoomGroupCreateDTO convert(RoomGroupCreateReqVO bean);

}
