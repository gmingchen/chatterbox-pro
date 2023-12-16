package com.slipper.modules.group.convert;

import com.slipper.modules.group.entity.GroupEntity;
import com.slipper.modules.group.model.dto.GroupCreateDTO;
import com.slipper.modules.group.model.req.GroupCreateReqVO;
import com.slipper.modules.group.model.req.GroupUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *  * 分组
 * @author gumingchen
 */
@Mapper
public interface GroupConvert {
    GroupConvert INSTANCE = Mappers.getMapper(GroupConvert.class);

    GroupCreateDTO convert(GroupCreateReqVO bean);

    GroupEntity convert(GroupUpdateReqVO bean);

    GroupEntity convert(GroupCreateDTO bean);
}
