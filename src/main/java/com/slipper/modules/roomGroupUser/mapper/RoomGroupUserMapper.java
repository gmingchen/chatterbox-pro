package com.slipper.modules.roomGroupUser.mapper;

import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.roomGroupUser.entity.RoomGroupUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 群房间_用户
 * @author gumingchen
 */
@Mapper
public interface RoomGroupUserMapper extends BaseMapperX<RoomGroupUserEntity> {

    /**
     * 通过群房间ID查询所有用户ID
     * @param roomGroupId 群房间ID
     * @return
     */
    List<Long> queryUserIdByRoomGroupId(@Param("roomGroupId") Long roomGroupId);
}
