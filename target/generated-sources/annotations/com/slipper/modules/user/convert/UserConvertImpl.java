package com.slipper.modules.user.convert;

import com.slipper.common.pojo.PageResult;
import com.slipper.modules.user.entity.UserEntity;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import com.slipper.modules.user.model.dto.UserCreateDTO;
import com.slipper.modules.user.model.req.UserUpdateReqVO;
import com.slipper.modules.user.model.res.UserSearchResVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T23:09:44+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_411 (Oracle Corporation)"
)
public class UserConvertImpl implements UserConvert {

    @Override
    public UserEntity convert(UserCreateDTO bean) {
        if ( bean == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setNickname( bean.getNickname() );
        userEntity.setAvatar( bean.getAvatar() );
        userEntity.setSex( bean.getSex() );
        userEntity.setEmail( bean.getEmail() );

        return userEntity;
    }

    @Override
    public UserEntity convert(UserUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setNickname( bean.getNickname() );
        userEntity.setAvatar( bean.getAvatar() );
        userEntity.setSex( bean.getSex() );

        return userEntity;
    }

    @Override
    public LoginUserDTO convert(UserEntity bean) {
        if ( bean == null ) {
            return null;
        }

        LoginUserDTO loginUserDTO = new LoginUserDTO();

        loginUserDTO.setId( bean.getId() );
        loginUserDTO.setNickname( bean.getNickname() );
        loginUserDTO.setAvatar( bean.getAvatar() );
        loginUserDTO.setSex( bean.getSex() );
        loginUserDTO.setOnline( bean.getOnline() );
        loginUserDTO.setEmail( bean.getEmail() );
        loginUserDTO.setLastAt( bean.getLastAt() );
        loginUserDTO.setCreatedAt( bean.getCreatedAt() );
        loginUserDTO.setStatus( bean.getStatus() );

        return loginUserDTO;
    }

    @Override
    public PageResult<UserSearchResVO> convert(PageResult<UserEntity> bean) {
        if ( bean == null ) {
            return null;
        }

        PageResult<UserSearchResVO> pageResult = new PageResult<UserSearchResVO>();

        pageResult.setTotal( bean.getTotal() );
        pageResult.setPages( bean.getPages() );
        pageResult.setList( userEntityListToUserSearchResVOList( bean.getList() ) );
        pageResult.setRequestId( bean.getRequestId() );

        return pageResult;
    }

    protected UserSearchResVO userEntityToUserSearchResVO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserSearchResVO userSearchResVO = new UserSearchResVO();

        userSearchResVO.setId( userEntity.getId() );
        userSearchResVO.setNickname( userEntity.getNickname() );
        userSearchResVO.setAvatar( userEntity.getAvatar() );
        userSearchResVO.setSex( userEntity.getSex() );
        userSearchResVO.setOnline( userEntity.getOnline() );

        return userSearchResVO;
    }

    protected List<UserSearchResVO> userEntityListToUserSearchResVOList(List<UserEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<UserSearchResVO> list1 = new ArrayList<UserSearchResVO>( list.size() );
        for ( UserEntity userEntity : list ) {
            list1.add( userEntityToUserSearchResVO( userEntity ) );
        }

        return list1;
    }
}
