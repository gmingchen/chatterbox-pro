package com.slipper.modules.roomGroupUser.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.message.model.res.MessageResVO;
import com.slipper.modules.roomGroupUser.entity.RoomGroupUserEntity;
import com.slipper.modules.roomGroupUser.model.res.RoomUserResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 群房间_用户
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface RoomGroupUserMapper extends BaseMapperX<RoomGroupUserEntity> {

    /**
     * 分页列表
     * @param size 数量
     * @param roomId 房间ID
     * @param lastId 上次查询上一条数据ID
     * @return
     */
    List<RoomUserResVO> queryPageByLastId(@Param("size") Long size, @Param("roomId") Long roomId, @Param("lastId") Long lastId);

    /**
     * 查询房间用户信息
     * @param roomId
     * @param userId
     * @return
     */
    RoomUserResVO queryInfo(@Param("roomId") Long roomId, @Param("userId") Long userId);

    /**
     * 查询群房间用户数量
     * @param roomId
     * @return
     */
    Long queryRoomGroupUserCount(@Param("roomId") Long roomId);
    /**
     * 查询群房间在线用户数量
     * @param roomId
     * @return
     */
    Long queryRoomGroupOnlineUserCount(@Param("roomId") Long roomId);

    /**
     * 查询所有群友ID
     * @param userId
     * @return
     */
    List<Long> queryGroupUserIds(@Param("userId") Long userId);
}
