package com.slipper.modules.roomGroupUser.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.roomGroupUser.entity.RoomGroupUserEntity;
import com.slipper.modules.roomGroupUser.model.req.RoomUserCountReqVO;
import com.slipper.modules.roomGroupUser.model.req.RoomUserPageReqVO;
import com.slipper.modules.roomGroupUser.model.res.RoomUserCountResVO;
import com.slipper.modules.roomGroupUser.model.res.RoomUserResVO;

import java.util.List;

/**
 * 群房间_用户
 * @author gumingchen
 */
public interface RoomGroupUserService extends IServiceX<RoomGroupUserEntity> {

    /**
     * 分页列表
     * @param reqVO 参数
     * @return
     */
    List<RoomUserResVO> queryPageByLastId(RoomUserPageReqVO reqVO);

    /**
     * ID查询信息
     * @param roomId 房间ID
     * @param userId 用户ID
     * @return
     */
    RoomUserResVO queryInfo(Long roomId, Long userId);

    /**
     * 查询群房间用户数量
     * @param reqVO 参数
     * @return
     */
    RoomUserCountResVO queryRoomGroupUserCount(RoomUserCountReqVO reqVO);

    /**
     * 查询所有群友ID
     * @param userId 用户ID
     * @return
     */
    List<Long> queryGroupUserIds(Long userId);

}
