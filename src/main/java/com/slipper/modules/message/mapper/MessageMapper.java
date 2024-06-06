package com.slipper.modules.message.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.message.entity.MessageEntity;
import com.slipper.modules.message.model.res.MessageResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 消息
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface MessageMapper extends BaseMapperX<MessageEntity> {

    /**
     * 分页列表
     * @param page 分页参数
     * @param roomId 房间ID
     * @return
     */
    IPage<MessageResVO> queryPage(Page<MessageEntity> page, @Param("roomId") Long roomId);

    /**
     * 分页列表
     * @param size 数量
     * @param roomId 房间ID
     * @param userId 用户ID
     * @param lastId 上次查询上一条数据ID
     * @return
     */
    List<MessageResVO> queryPageByLastId(@Param("size") Long size, @Param("roomId") Long roomId, @Param("userId") Long userId, @Param("lastId") Long lastId);

    /**
     * 信息
     * @param id ID
     * @return
     */
    MessageResVO queryInfo(@Param("id") Long id);
}
