package com.slipper.modules.user.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.message.entity.MessageEntity;
import com.slipper.modules.user.entity.UserEntity;
import com.slipper.modules.user.model.res.UserPageResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface UserMapper extends BaseMapperX<UserEntity> {
    /**
     * 分页
     * @param page 分页参数
     * @param userId 用户ID
     * @param nickname 昵称
     * @return
     */
    IPage<UserPageResVO> queryPage(Page<UserEntity> page,
                                   @Param("userId") Long userId,
                                   @Param("nickname") String nickname);

}
