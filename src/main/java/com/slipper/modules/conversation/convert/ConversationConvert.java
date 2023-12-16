package com.slipper.modules.conversation.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 会话
 * @author gumingchen
 */
@Mapper
public interface ConversationConvert {
    ConversationConvert INSTANCE = Mappers.getMapper(ConversationConvert.class);
}
