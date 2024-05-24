package com.slipper.modules.expression.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.expression.entity.ExpressionEntity;
import com.slipper.modules.expression.model.res.ExpressionSelectResVO;

import java.util.List;

/**
 * 表情
 * @author gumingchen
 */
public interface ExpressionService extends IServiceX<ExpressionEntity> {

    /**
     * 列表
     * @return
     */
    List<ExpressionSelectResVO> selectList();
}
