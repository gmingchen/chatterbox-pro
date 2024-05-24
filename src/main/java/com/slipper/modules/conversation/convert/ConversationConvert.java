package com.slipper.modules.conversation.convert;

import com.slipper.modules.conversation.entity.ConversationEntity;
import com.slipper.modules.conversation.model.dto.ConversationCreateDTO;
import com.slipper.modules.conversation.model.req.ConversationCreateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 会话
 * @author gumingchen
 */
@Mapper
public interface ConversationConvert {
    ConversationConvert INSTANCE = Mappers.getMapper(ConversationConvert.class);

    ConversationEntity convert(ConversationCreateDTO bean);
}
