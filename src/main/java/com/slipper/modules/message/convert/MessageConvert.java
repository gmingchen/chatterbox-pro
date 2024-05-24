package com.slipper.modules.message.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 消息
 * @author gumingchen
 */
@Mapper
public interface MessageConvert {
    MessageConvert INSTANCE = Mappers.getMapper(MessageConvert.class);
}
