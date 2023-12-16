package com.slipper.modules.friend.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.friend.entity.FriendEntity;
import com.slipper.modules.friend.model.dto.FriendCreateDTO;
import com.slipper.modules.friend.model.res.FriendResVO;

import java.util.List;

/**
 * 好友
 * @author gumingchen
 */
public interface FriendService extends IServiceX<FriendEntity> {

    /**
     * 校验是否我的好友
     * @param userId 用户ID
     * @param friendId 好友ID
     * @return
     */
    boolean validateFriendMy(Long userId, Long friendId);

    /**
     * 校验是否是对方好友
     * @param userId 用户ID
     * @param friendId 好友ID
     * @return
     */
    boolean validateFriendOther(Long userId, Long friendId);

    /**
     * 校验是否互为好友
     * @param userId 用户ID
     * @param friendId 好友ID
     * @return
     */
    boolean validateFriendEachOther(Long userId, Long friendId);

    /**
     * 分组ID查询好友数量
     * @param groupId 分组ID
     * @return
     */
    Long queryCountByGroupId(Long groupId);

    /**
     * 校验分组是否存在好友
     * @param groupId 分组ID
     * @return
     */
    boolean validateGroupExistFriend(Long groupId);

    /**
     * 新增好友关系
     * @param friendCreateDTO 参数
     */
    void beFriend(FriendCreateDTO friendCreateDTO);

}
