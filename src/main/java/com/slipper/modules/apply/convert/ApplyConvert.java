package com.slipper.modules.apply.convert;

import com.slipper.modules.apply.entity.ApplyEntity;
import com.slipper.modules.apply.model.req.ApplyFriendReqVO;
import com.slipper.modules.apply.model.req.ApplyGroupReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 申请
 * @author gumingchen
 */
@Mapper
public interface ApplyConvert {
    ApplyConvert INSTANCE = Mappers.getMapper(ApplyConvert.class);

    ApplyEntity convert(ApplyFriendReqVO bean);

    ApplyEntity convert(ApplyGroupReqVO bean);
}
