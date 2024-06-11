package com.slipper.modules.grouping.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.grouping.entity.GroupingEntity;
import com.slipper.modules.grouping.model.req.GroupingCreateReqVO;
import com.slipper.modules.grouping.model.req.GroupingUpdateReqVO;
import com.slipper.modules.grouping.model.res.GroupingFriendResVO;
import com.slipper.modules.grouping.model.res.GroupingSelectResVO;

import java.util.List;

/**
 * 分组
 * @author gumingchen
 */
public interface GroupingService extends IServiceX<GroupingEntity> {

    /**
     * 新增默认分组
     * @param userId 用户ID
     * @return
     */
    Long createDefault(Long userId);

    /**
     * 新增
     * @param reqVO 分组参数
     * @return
     */
    Long create(GroupingCreateReqVO reqVO);

    /**
     * 更新
     * @param reqVO 分组参数
     */
    void update(GroupingUpdateReqVO reqVO);

    /**
     * 获取分组选择列表
     * @return
     */
    List<GroupingSelectResVO> selectList();

    /**
     * 查询固定的分组
     * @param userId 用户ID
     * @return
     */
    GroupingEntity queryFixed(Long userId);

    /**
     * 查询分组好友列表
     * @return
     */
    List<GroupingFriendResVO> queryList();

    /**
     * 查询分组好友信息
     * @param userId 用户ID
     * @param friendId 好友ID
     * @return
     */
    GroupingFriendResVO queryInfo(Long userId, Long friendId);

}
