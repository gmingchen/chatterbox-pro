package com.slipper.modules.user.convert;

import com.slipper.modules.user.entity.UserEntity;
import com.slipper.modules.user.model.dto.UserCreateDTO;
import com.slipper.modules.user.model.req.UserUpdateReqVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-16T10:45:35+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
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
        userEntity.setOnline( bean.getOnline() );
        userEntity.setOpenId( bean.getOpenId() );
        userEntity.setLastAt( bean.getLastAt() );

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
}
