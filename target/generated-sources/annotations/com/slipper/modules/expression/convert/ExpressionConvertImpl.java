package com.slipper.modules.expression.convert;

import com.slipper.modules.expression.entity.ExpressionEntity;
import com.slipper.modules.expression.model.res.ExpressionSelectResVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-03T16:58:51+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class ExpressionConvertImpl implements ExpressionConvert {

    @Override
    public List<ExpressionSelectResVO> convert(List<ExpressionEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<ExpressionSelectResVO> list1 = new ArrayList<ExpressionSelectResVO>( list.size() );
        for ( ExpressionEntity expressionEntity : list ) {
            list1.add( expressionEntityToExpressionSelectResVO( expressionEntity ) );
        }

        return list1;
    }

    protected ExpressionSelectResVO expressionEntityToExpressionSelectResVO(ExpressionEntity expressionEntity) {
        if ( expressionEntity == null ) {
            return null;
        }

        ExpressionSelectResVO expressionSelectResVO = new ExpressionSelectResVO();

        expressionSelectResVO.setId( expressionEntity.getId() );
        expressionSelectResVO.setUrl( expressionEntity.getUrl() );
        expressionSelectResVO.setContent( expressionEntity.getContent() );
        expressionSelectResVO.setType( expressionEntity.getType() );

        return expressionSelectResVO;
    }
}
