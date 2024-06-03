package com.slipper.modules.grouping.convert;

import com.slipper.modules.grouping.entity.GroupingEntity;
import com.slipper.modules.grouping.model.req.GroupingCreateReqVO;
import com.slipper.modules.grouping.model.req.GroupingUpdateReqVO;
import com.slipper.modules.grouping.model.res.GroupingSelectResVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
<<<<<<< HEAD
    date = "2024-06-03T16:58:51+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
=======
    date = "2024-05-30T23:09:44+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_411 (Oracle Corporation)"
>>>>>>> 2c40374883c1479447478e870711c7435af9145c
=======
    date = "2024-05-30T23:09:44+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_411 (Oracle Corporation)"
>>>>>>> 2c40374883c1479447478e870711c7435af9145c
)
public class GroupingConvertImpl implements GroupingConvert {

    @Override
    public GroupingEntity convert(GroupingCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        GroupingEntity groupingEntity = new GroupingEntity();

        groupingEntity.setName( bean.getName() );

        return groupingEntity;
    }

    @Override
    public GroupingEntity convert(GroupingUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        GroupingEntity groupingEntity = new GroupingEntity();

        groupingEntity.setId( bean.getId() );
        groupingEntity.setName( bean.getName() );

        return groupingEntity;
    }

    @Override
    public List<GroupingSelectResVO> convert(List<GroupingEntity> bean) {
        if ( bean == null ) {
            return null;
        }

        List<GroupingSelectResVO> list = new ArrayList<GroupingSelectResVO>( bean.size() );
        for ( GroupingEntity groupingEntity : bean ) {
            list.add( groupingEntityToGroupingSelectResVO( groupingEntity ) );
        }

        return list;
    }

    protected GroupingSelectResVO groupingEntityToGroupingSelectResVO(GroupingEntity groupingEntity) {
        if ( groupingEntity == null ) {
            return null;
        }

        GroupingSelectResVO groupingSelectResVO = new GroupingSelectResVO();

        groupingSelectResVO.setId( groupingEntity.getId() );
        groupingSelectResVO.setName( groupingEntity.getName() );

        return groupingSelectResVO;
    }
}
