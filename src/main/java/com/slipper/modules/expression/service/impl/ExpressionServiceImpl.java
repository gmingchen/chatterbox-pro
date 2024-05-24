package com.slipper.modules.expression.service.impl;

import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.modules.expression.convert.ExpressionConvert;
import com.slipper.modules.expression.entity.ExpressionEntity;
import com.slipper.modules.expression.mapper.ExpressionMapper;
import com.slipper.modules.expression.model.res.ExpressionSelectResVO;
import com.slipper.modules.expression.service.ExpressionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 表情
 * @author gumingchen
 */
@Service("expressionService")
public class ExpressionServiceImpl extends ServiceImplX<ExpressionMapper, ExpressionEntity> implements ExpressionService {

    @Override
    public List<ExpressionSelectResVO> selectList() {
        return ExpressionConvert.INSTANCE.convert(this.list());
    }
}
