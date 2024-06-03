package com.slipper.modules.apply.convert;

import com.slipper.modules.apply.entity.ApplyEntity;
import com.slipper.modules.apply.model.req.ApplyFriendReqVO;
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
public class ApplyConvertImpl implements ApplyConvert {

    @Override
    public ApplyEntity convert(ApplyFriendReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        ApplyEntity applyEntity = new ApplyEntity();

        applyEntity.setContent( bean.getContent() );
        applyEntity.setTargetId( bean.getTargetId() );
        applyEntity.setGroupingId( bean.getGroupingId() );
        applyEntity.setRemark( bean.getRemark() );

        return applyEntity;
    }
}
