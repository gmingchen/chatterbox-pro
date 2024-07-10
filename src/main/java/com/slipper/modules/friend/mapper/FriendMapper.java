package com.slipper.modules.friend.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.friend.entity.FriendEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 好友
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface FriendMapper extends BaseMapperX<FriendEntity> {

    /**
     * 查询所有好友ID
     * @param userId 用户ID
     * @return
     */
    List<Long> queryFriendIds(@Param("userId") Long userId);
}
