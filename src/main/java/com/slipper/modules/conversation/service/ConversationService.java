package com.slipper.modules.conversation.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.conversation.entity.ConversationEntity;
import com.slipper.modules.conversation.model.dto.ConversationCreateDTO;
import com.slipper.modules.conversation.model.req.ConversationCreateReqVO;
import com.slipper.modules.conversation.model.req.ConversationDeleteReqVO;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会话
 * @author gumingchen
 */
public interface ConversationService extends IServiceX<ConversationEntity> {

    /**
     * 获取会话列表
     * @return
     */
    List<ConversationResVO> queryList();

    /**
     * 获取会话信息
     * @param id 会话ID
     * @return
     */
    ConversationResVO queryInfo(Long id);

    /**
     * 获取会话信息
     * @param roomId 房间ID
     * @param userId 用户ID
     * @return
     */
    ConversationResVO queryInfo(Long roomId, Long userId);

    /**
     * 通过ID查询信息
     * @param ids 会话ID数组
     * @return
     */
    List<ConversationResVO> queryListByIds(List<Long> ids);

    /**
     * 新增会话
     * @param roomId 房间ID
     * @param userIds 用户ID数组
     * @return
     */
    List<Long> create(Long roomId, List<Long> userIds);

    /**
     * 新增会话
     * @param reqVO 参数
     * @return
     */
    ConversationResVO create(ConversationCreateReqVO reqVO);

    /**
     * 删除会话
     * @param reqVO 参数
     */
    void delete(ConversationDeleteReqVO reqVO);
}
