package com.slipper.modules.group.convert;

import com.slipper.modules.group.entity.GroupEntity;
import com.slipper.modules.group.model.dto.GroupCreateDTO;
import com.slipper.modules.group.model.req.GroupCreateReqVO;
import com.slipper.modules.group.model.req.GroupUpdateReqVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-16T10:45:35+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class GroupConvertImpl implements GroupConvert {

    @Override
    public GroupCreateDTO convert(GroupCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        GroupCreateDTO groupCreateDTO = new GroupCreateDTO();

        groupCreateDTO.setName( bean.getName() );

        return groupCreateDTO;
    }

    @Override
    public GroupEntity convert(GroupUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        GroupEntity groupEntity = new GroupEntity();

        groupEntity.setId( bean.getId() );
        groupEntity.setName( bean.getName() );

        return groupEntity;
    }

    @Override
    public GroupEntity convert(GroupCreateDTO bean) {
        if ( bean == null ) {
            return null;
        }

        GroupEntity groupEntity = new GroupEntity();

        groupEntity.setUserId( bean.getUserId() );
        groupEntity.setName( bean.getName() );
        groupEntity.setSort( bean.getSort() );
        groupEntity.setFixed( bean.getFixed() );

        return groupEntity;
    }
}
