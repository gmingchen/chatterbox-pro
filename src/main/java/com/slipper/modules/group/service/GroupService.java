package com.slipper.modules.group.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.group.entity.GroupEntity;
import com.slipper.modules.group.model.dto.GroupCreateDTO;
import com.slipper.modules.group.model.req.GroupCreateReqVO;
import com.slipper.modules.group.model.req.GroupDeleteReqVO;
import com.slipper.modules.group.model.req.GroupSortReqVO;
import com.slipper.modules.group.model.req.GroupUpdateReqVO;
import com.slipper.modules.group.model.res.GroupResVO;
import com.slipper.modules.room.entity.RoomEntity;

import java.util.List;

/**
 * 分组
 * @author gumingchen
 */
public interface GroupService extends IServiceX<GroupEntity> {

    /**
     * 用户分组列表
     * @return
     */
    List<GroupResVO> queryList();

    /**
     * 创建
     * @param reqVO 参数
     * @return
     */
    Long create(GroupCreateReqVO reqVO);

    /**
     * 编辑
     * @param reqVO 参数
     */
    void update(GroupUpdateReqVO reqVO);

    /**
     * 删除
     * @param reqVO 参数
     */
    void delete(GroupDeleteReqVO reqVO);

    /**
     * 排序
     * @param reqVO 参数
     */
    void sort(GroupSortReqVO reqVO);

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * 新增
     * @param createDTO 参数
     * @return
     */
    GroupEntity create(GroupCreateDTO createDTO);
}
