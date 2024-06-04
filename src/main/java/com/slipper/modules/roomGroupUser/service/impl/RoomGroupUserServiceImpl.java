package com.slipper.modules.roomGroupUser.service.impl;

import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.modules.roomGroupUser.entity.RoomGroupUserEntity;
import com.slipper.modules.roomGroupUser.mapper.RoomGroupUserMapper;
import com.slipper.modules.roomGroupUser.model.req.RoomUserPageReqVO;
import com.slipper.modules.roomGroupUser.model.res.RoomUserResVO;
import com.slipper.modules.roomGroupUser.service.RoomGroupUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 群房间_用户
 * @author gumingchen
 */
@Service("roomGroupUserService")
public class RoomGroupUserServiceImpl extends ServiceImplX<RoomGroupUserMapper, RoomGroupUserEntity> implements RoomGroupUserService {

    @Override
    public List<RoomUserResVO> queryPageByLastId(RoomUserPageReqVO reqVO) {
        return baseMapper.queryPageByLastId(reqVO.getSize(), reqVO.getRoomId(), reqVO.getLastId());
    }
}
