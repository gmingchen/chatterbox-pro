package com.slipper.modules.applyUser.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 申请_用户
 * @author gumingchen
 */
@Mapper
public interface ApplyUserConvert {
    ApplyUserConvert INSTANCE = Mappers.getMapper(ApplyUserConvert.class);
}
