package com.slipper.modules.message.convert;

import com.slipper.modules.message.entity.MessageEntity;
import com.slipper.modules.message.model.dto.MessageCreateDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-16T10:45:35+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class MessageConvertImpl implements MessageConvert {

    @Override
    public MessageEntity convert(MessageCreateDTO bean) {
        if ( bean == null ) {
            return null;
        }

        MessageEntity messageEntity = new MessageEntity();

        messageEntity.setUserId( bean.getUserId() );
        messageEntity.setRoomId( bean.getRoomId() );
        messageEntity.setType( bean.getType() );

        return messageEntity;
    }
}
