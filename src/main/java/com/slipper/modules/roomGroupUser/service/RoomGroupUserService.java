package com.slipper.modules.roomGroupUser.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.roomGroupUser.entity.RoomGroupUserEntity;
import com.slipper.modules.roomGroupUser.model.req.RoomUserPageReqVO;
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

}
