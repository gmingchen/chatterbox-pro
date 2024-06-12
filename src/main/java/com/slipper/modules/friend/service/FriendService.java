package com.slipper.modules.friend.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.friend.entity.FriendEntity;
import com.slipper.modules.friend.model.dto.FriendCreateDTO;
import com.slipper.modules.friend.model.req.FriendDeleteReqVO;

/**
 * 好友
 * @author gumingchen
 */
public interface FriendService extends IServiceX<FriendEntity> {

    /**
     * 新增好友关系
     * @param dto 好友参数
     */
    void create(FriendCreateDTO dto);

    /**
     * 删除好友关系
     * @param reqVO 参数
     */
    void delete(FriendDeleteReqVO reqVO);

    /**
     * 校验是否是好友： target 是否是 source 的好友
     * @param sourceId 用户ID
     * @param targetId 目标用户ID
     * @return
     */
    Boolean validateFriend(Long sourceId, Long targetId);

    /**
     * 校验是否互为好友
     * @param sourceId 用户ID
     * @param targetId 目标用户ID
     * @return
     */
    Boolean validateFriendBoth(Long sourceId, Long targetId);

}
