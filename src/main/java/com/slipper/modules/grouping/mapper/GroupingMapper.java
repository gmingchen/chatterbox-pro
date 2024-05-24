package com.slipper.modules.grouping.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.annotation.DataPermissionAlias;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.grouping.entity.GroupingEntity;
import com.slipper.modules.grouping.model.res.GroupingFriendResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 分组
 * @author gumingchen
 */
@Mapper
public interface GroupingMapper extends BaseMapperX<GroupingEntity> {

    /**
     * 查询固定分组ID
     * @param userId 用户ID
     * @return
     */
    @InterceptorIgnore(dataPermission = "true")
    @Select("SELECT * FROM `grouping` WHERE user_id = #{ userId } AND fixed = 1")
    GroupingEntity queryFixed(@Param("userId") Long userId);

    /**
     * 查询分组好友列表
     * @param userId 用户ID
     * @return
     */
    @InterceptorIgnore(dataPermission = "true")
    List<GroupingFriendResVO> queryList(@Param("userId") Long userId);
}
