package com.slipper.modules.message.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.message.entity.MessageEntity;
import com.slipper.modules.message.model.req.MessagePageReqVO;
import com.slipper.modules.message.model.res.MessageResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 消息
 * @author gumingchen
 */
@Mapper
public interface MessageMapper extends BaseMapperX<MessageEntity> {

    /**
     * 分页列表
     * @param page 分页参数
     * @param roomId 房间ID
     * @return
     */
    IPage<MessageResVO> queryPage(Page<MessageEntity> page, @Param("roomId") Long roomId);
}
