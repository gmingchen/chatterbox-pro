package com.slipper.modules.conversation.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.annotation.DataPermissionAlias;
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
     * 用户会话列表
     * @param userId 用户ID
     * @return
     */
    List<ConversationResVO> queryList(@Param("userId") Long userId);
}
