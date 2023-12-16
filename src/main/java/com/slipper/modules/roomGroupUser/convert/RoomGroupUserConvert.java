package com.slipper.modules.roomGroupUser.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 群房间_用户
 * @author gumingchen
 */
@Mapper
public interface RoomGroupUserConvert {
    RoomGroupUserConvert INSTANCE = Mappers.getMapper(RoomGroupUserConvert.class);
}
