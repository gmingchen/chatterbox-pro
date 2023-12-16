package com.slipper.modules.group.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import com.slipper.modules.group.entity.GroupEntity;
import com.slipper.modules.group.model.res.GroupResVO;
import com.slipper.modules.room.entity.RoomEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分组
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface GroupMapper extends BaseMapperX<GroupEntity> {

    /**
     * 用户分组列表
     * @param userId 用户ID
     * @return
     */
    List<GroupResVO> queryList(@Param("userId") Long userId);

}
