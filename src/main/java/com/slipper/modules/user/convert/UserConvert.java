package com.slipper.modules.user.convert;

import com.slipper.common.pojo.PageResult;
import com.slipper.modules.user.entity.UserEntity;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import com.slipper.modules.user.model.dto.UserCreateDTO;
import com.slipper.modules.user.model.dto.UserInfoDTO;
import com.slipper.modules.user.model.req.UserUpdateReqVO;
import com.slipper.modules.user.model.res.UserSearchResVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户
 * @author gumingchen
 */
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserEntity convert(UserCreateDTO bean);

    UserEntity convert(UserUpdateReqVO bean);

    LoginUserDTO convert(UserEntity bean);

    UserInfoDTO convertInfo(UserEntity bean);

    PageResult<UserSearchResVO> convert(PageResult<UserEntity> bean);
}
