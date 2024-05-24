package com.slipper.modules.apply.convert;

import com.slipper.modules.apply.entity.ApplyEntity;
import com.slipper.modules.apply.model.req.ApplyFriendReqVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-22T16:29:49+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
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
