package com.slipper.modules.apply.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.apply.entity.ApplyEntity;
import com.slipper.modules.apply.model.res.ApplyInfoRes;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 申请
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface ApplyMapper extends BaseMapperX<ApplyEntity> {

    /**
     * 分页列表
     * @param size 数量
     * @param userId 用户ID
     * @param lastId 上次查询上一条数据ID
     * @return
     */
    List<ApplyInfoRes> queryPageByLastId(@Param("size") Long size, @Param("userId") Long userId, @Param("lastId") Long lastId);
    /**
     * 信息
     * @param id 会话ID
     * @return
     */
    ApplyInfoRes queryInfo(@Param("id") Long id);

    /**
     * 更具状态查询数据
     * @param userId 用户ID
     * @param status 状态
     * @return
     */
    Long queryCountByStatus(@Param("userId") Long userId, @Param("status") Integer status);
}
