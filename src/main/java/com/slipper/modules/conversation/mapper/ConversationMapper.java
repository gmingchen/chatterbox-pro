package com.slipper.modules.conversation.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.conversation.entity.ConversationEntity;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会话
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface ConversationMapper extends BaseMapperX<ConversationEntity> {

    /**
     * 列表
     * @param userId 用户ID
     * @return
     */
    @InterceptorIgnore(dataPermission = "true")
    List<ConversationResVO> queryList(@Param("userId") Long userId);

    /**
     * 信息
     * @param id 会话ID
     * @return
     */
    @InterceptorIgnore(dataPermission = "true")
    ConversationResVO queryInfo(@Param("id") Long id);

    /**
     * 信息
     * @param roomId 房间ID
     * @param userId 用户ID
     * @return
     */
    @InterceptorIgnore(dataPermission = "true")
    ConversationResVO queryInfoByRoomIdAndUserId(@Param("roomId") Long roomId, @Param("userId") Long userId);

    /**
     * 通过ID查询信息
     * @param ids 会话ID数组
     * @return
     */
    @InterceptorIgnore(dataPermission = "true")
    List<ConversationResVO> queryListByIds(@Param("ids") List<Long> ids);
}
