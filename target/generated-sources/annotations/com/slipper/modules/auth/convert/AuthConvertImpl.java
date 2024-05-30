package com.slipper.modules.auth.convert;

import com.slipper.modules.auth.model.req.AuthRegisterReqVO;
import com.slipper.modules.user.entity.UserEntity;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import com.slipper.modules.user.model.dto.UserCreateDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T23:09:44+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_411 (Oracle Corporation)"
)
public class AuthConvertImpl implements AuthConvert {

    @Override
    public UserCreateDTO convert(AuthRegisterReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        UserCreateDTO userCreateDTO = new UserCreateDTO();

        userCreateDTO.setNickname( bean.getNickname() );
        userCreateDTO.setAvatar( bean.getAvatar() );
        userCreateDTO.setSex( bean.getSex() );
        userCreateDTO.setEmail( bean.getEmail() );

        return userCreateDTO;
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
}
