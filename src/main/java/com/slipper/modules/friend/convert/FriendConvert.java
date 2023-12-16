package com.slipper.modules.friend.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 好友
 * @author gumingchen
 */
@Mapper
public interface FriendConvert {
    FriendConvert INSTANCE = Mappers.getMapper(FriendConvert.class);
}
