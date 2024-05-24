package com.slipper.modules.grouping.convert;

import com.slipper.modules.grouping.entity.GroupingEntity;
import com.slipper.modules.grouping.model.req.GroupingCreateReqVO;
import com.slipper.modules.grouping.model.req.GroupingUpdateReqVO;
import com.slipper.modules.grouping.model.res.GroupingSelectResVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 分组
 * @author gumingchen
 */
@Mapper
public interface GroupingConvert {
    GroupingConvert INSTANCE = Mappers.getMapper(GroupingConvert.class);

    GroupingEntity convert(GroupingCreateReqVO bean);

    GroupingEntity convert(GroupingUpdateReqVO bean);

    List<GroupingSelectResVO> convert(List<GroupingEntity> bean);
}
