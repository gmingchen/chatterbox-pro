package com.slipper.modules.conversation.convert;

import com.slipper.modules.conversation.entity.ConversationEntity;
import com.slipper.modules.conversation.model.dto.ConversationCreateDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-03T16:58:51+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class ConversationConvertImpl implements ConversationConvert {

    @Override
    public ConversationEntity convert(ConversationCreateDTO bean) {
        if ( bean == null ) {
            return null;
        }

        ConversationEntity conversationEntity = new ConversationEntity();

        conversationEntity.setRoomId( bean.getRoomId() );

        return conversationEntity;
    }
}
