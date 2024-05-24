package com.slipper.modules.roomGroup.convert;

import com.slipper.modules.roomGroup.entity.RoomGroupEntity;
import com.slipper.modules.roomGroup.model.dto.RoomGroupCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 群房间
 * @author gumingchen
 */
@Mapper
public interface RoomGroupConvert {
    RoomGroupConvert INSTANCE = Mappers.getMapper(RoomGroupConvert.class);

    RoomGroupEntity convert(RoomGroupCreateDTO bean);
}
