package com.slipper.core.mybatisplus.expend.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.common.utils.CollectionUtils;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.core.mybatisplus.expend.wrapper.LambdaQueryWrapperX;
import com.slipper.exception.RunException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * @author gumingchen
 */
public class ServiceImplX<M extends BaseMapperX<T>, T> extends ServiceImpl<M, T> implements IServiceX<T> {

    /**
     * 批量插入 删除
     * 已存在的不进行操作
     * @param primaryId 主要的ID
     * @param modifiedIds 要修改的ID数组
     * @param selectEqColumnFn 查询条件的列
     * @param modifiedIdMap 筛选修改列的函数
     * @param insertMap 插入的函数
     * @param deleteInColumnFn 删除条件的列
     */
    @Override
    @Transactional(rollbackFor = RunException.class)
    public void saveOrRemoveBatch(
            Serializable primaryId,
            Collection<?> modifiedIds,
            SFunction<T, ?> selectEqColumnFn,
            Function<T, Serializable> modifiedIdMap,
            Function<Serializable, T> insertMap,
            SFunction<T, ?> deleteInColumnFn) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapperX<T>()
                .eq(selectEqColumnFn, primaryId);
        Collection<Serializable> allIds = CollectionUtils.mapList(baseMapper.selectList(wrapper), modifiedIdMap);

        List<Serializable> ids = CollectionUtils.mapList(modifiedIds, Serializable.class::cast);
        List<Serializable> createIds = CollectionUtils.subtractToList(ids, allIds);
        List<Serializable> deleteIds = CollectionUtils.subtractToList(allIds, ids);

        if (CollectionUtils.isNotEmpty(createIds)) {
            baseMapper.insertBatch(
                    CollectionUtils.mapList(createIds, insertMap)
            );
        }
        if (CollectionUtils.isNotEmpty(deleteIds)) {
            LambdaUpdateWrapper<T> updateWrapper = new LambdaUpdateWrapper<T>()
                    .eq(selectEqColumnFn, primaryId)
                    .in(deleteInColumnFn, deleteIds);
            baseMapper.delete(updateWrapper);
        }
    }

    /**
     * 校验是否存在
     * @param id ID
     * @return
     */
    @Override
    public boolean validateIsExist(Serializable id) {
        T entity = baseMapper.selectById(id);
        return entity != null;
    }

    /**
     * 校验是否存在
     * @param id ID
     * @return
     */
    @Override
    public T validateExist(Serializable id) {
        T entity = baseMapper.selectById(id);
        if (entity == null) {
            throw new RunException(ResultCodeEnum.NOT_EXIST);
        }
        return entity;
    }

    /**
     * 校验是否存在
     * @param selectEqColumnFn 查询条件的列
     * @param selectEqColumnValue 查询的值
     * @return
     */
    @Override
    public boolean validateIsExist(SFunction<T, ?> selectEqColumnFn, Object selectEqColumnValue) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapperX<T>()
                .eq(selectEqColumnFn, selectEqColumnValue);
        T entity = baseMapper.selectOne(wrapper);
        return entity != null;
    }
    /**
     * 校验是否存在
     * @param selectEqColumnFn 查询条件的列
     * @param selectEqColumnValue 查询的值
     * @return
     */
    @Override
    public boolean validateExist(SFunction<T, ?> selectEqColumnFn, Object selectEqColumnValue) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapperX<T>()
                .eq(selectEqColumnFn, selectEqColumnValue);
        T entity = baseMapper.selectOne(wrapper);
        if (entity != null) {
            throw new RunException(ResultCodeEnum.EXISTED_ERROR);
        }
        return true;
    }
}
