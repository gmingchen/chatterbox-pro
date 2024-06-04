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
}
