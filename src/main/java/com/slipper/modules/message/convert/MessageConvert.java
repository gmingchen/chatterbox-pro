package com.slipper.modules.message.convert;

import com.slipper.modules.message.model.dto.MessageCreateDTO;
import com.slipper.modules.message.model.req.MessageCreateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 消息
 * @author gumingchen
 */
@Mapper
public interface MessageConvert {
    MessageConvert INSTANCE = Mappers.getMapper(MessageConvert.class);

    MessageCreateDTO convert(MessageCreateReqVO bean);
}
