package com.slipper.modules.media.convert;

import com.slipper.modules.media.model.res.MediaResVO;
import com.slipper.modules.message.model.dto.MessageCreateDTO;
import com.slipper.modules.message.model.req.MessageCreateReqVO;
import com.slipper.modules.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 媒体
 * @author gumingchen
 */
@Mapper
public interface MediaConvert {
    MediaConvert INSTANCE = Mappers.getMapper(MediaConvert.class);

    MediaResVO convert(UserEntity bean);
}
