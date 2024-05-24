package com.slipper.modules.expression.convert;

import com.slipper.modules.expression.entity.ExpressionEntity;
import com.slipper.modules.expression.model.res.ExpressionSelectResVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 表情
 * @author gumingchen
 */
@Mapper
public interface ExpressionConvert {
    ExpressionConvert INSTANCE = Mappers.getMapper(ExpressionConvert.class);

    List<ExpressionSelectResVO> convert(List<ExpressionEntity> list);
}
